package com.example.spring.stack;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Anthony
 * @date 2019/10/9
 * @description spark任务优先级算法
 * #demo  8core running_job_collection / hang_job_collection
 * #demo
 */
@Service
public class SparkJobPriorityAlgorithm {

    private static final String RUNNING_SPARK_JOB_COLLECTION = "RUNNING_SPARK_JOB_COLLECTION";
    private static final String HANG_SPARK_JOB_COLLECTION = "HANG_SPARK_JOB_COLLECTION";
    private static final String SPARK_JOB_CREATE_TIME = "SPARK_JOB_CREATE_TIME";
    //分割请求段的时间间隔(ms)
    private static final long TIME_INTERVAL = 30;
    //进程沉睡时间(ms)
    private static final long SLEEP_TIME = 20;

    private static final int TOTAL_RESOURCE = 8;

    private static final int TOTAL_LEVEL = 5;

    //优先级比例从高到低
    private static final int[] LEVEL_RESOURCE_PROPORT = new int[]{2, 2, 2, 1, 1};

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断运行队列是否已满
     *
     * @return
     */
    public boolean isFullRunningQueue() {

        Set<Object> keys = redisTemplate.opsForZSet().reverseRange(RUNNING_SPARK_JOB_COLLECTION, 0, -1);
        if (keys == null || keys.size() < TOTAL_RESOURCE) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 任务排序
     *
     * @param sparkJobInfo 任务信息
     */
    public String orderJob(SparkJobInfo sparkJobInfo) {
        //获取任务优先级
        int priority = sparkJobInfo.getLevel();
        //生成唯一jobId
        String jobId = sparkJobInfo.getDatasetId() + UUID.randomUUID().toString();

        //设置请求时间区间开始时间
        Set<Object> range = redisTemplate.opsForZSet().reverseRange(HANG_SPARK_JOB_COLLECTION, 0, -1);
        if (range == null || range.size() == 0)
            redisTemplate.opsForValue().set(SPARK_JOB_CREATE_TIME, sparkJobInfo.getCreateTime());

        long createTime = (long) redisTemplate.opsForValue().get(SPARK_JOB_CREATE_TIME);
        //根据请求时间区间对请求进行分段，每30ms的请求数为一个请求段
        while ((sparkJobInfo.getCreateTime() - createTime > TIME_INTERVAL) && range != null && range.size() != 0) {
            try {
                Thread.sleep(SLEEP_TIME);
                range = redisTemplate.opsForZSet().reverseRange(HANG_SPARK_JOB_COLLECTION, 0, -1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //在上一段请求队列执行完之后重试设置请求时间区间的开始时间
        if (range == null || range.size() == 0)
            redisTemplate.opsForValue().set(SPARK_JOB_CREATE_TIME, sparkJobInfo.getCreateTime());

        //将任务存进任务等待队列
        redisTemplate.opsForZSet().add(HANG_SPARK_JOB_COLLECTION, jobId, priority);
        return jobId;
    }

    /**
     * 删除等待队列中的任务
     *
     * @param jobId 任务id
     */
    public void removeWaitJob(String jobId) {
        redisTemplate.opsForZSet().remove(HANG_SPARK_JOB_COLLECTION, jobId);
    }

    /**
     * 任务拦截处理
     *
     * @param sparkJobInfo
     */
    public void doAroundJob(SparkJobInfo sparkJobInfo) {
        //将任务添加到等待队列
        String jobId = orderJob(sparkJobInfo);
        //从redis中获取等待队列
        Set<Object> range = redisTemplate.opsForZSet().reverseRange(HANG_SPARK_JOB_COLLECTION, 0, -1);
        //获取等待队列排列第一的jobId
        assert range != null;
        String jobIdQueue = String.valueOf(range.toArray()[0]);
        try {
            //如果当前任务没有排在队列的首部等待20ms
            while (!Objects.equals(jobIdQueue, jobId)) {
                Thread.sleep(SLEEP_TIME);
                range = redisTemplate.opsForZSet().reverseRange(HANG_SPARK_JOB_COLLECTION, 0, -1);
                assert range != null;
                jobIdQueue = String.valueOf(range.toArray()[0]);
            }
            //如果运行队列已满 等待20ms
            while (isFullRunningQueue()) {
                Thread.sleep(SLEEP_TIME);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //放行，将任务从等待队列中删除
        removeWaitJob(jobId);
    }

    public void doAroundJob_v2(SparkJobInfo sparkJobInfo) {
        //将任务添加到等待队列
        String jobId = orderJob(sparkJobInfo);
        int level = sparkJobInfo.getLevel();
        //从redis中获取等待队列
        Set<ZSetOperations.TypedTuple<Object>> hang_spark_job_collection = redisTemplate.opsForZSet().reverseRangeWithScores(HANG_SPARK_JOB_COLLECTION, 0, -1);
        TreeMap<Double, List<String>> map = new TreeMap<>();
        assert hang_spark_job_collection != null;
        ZSetOperations.TypedTuple<Object> tuple = (ZSetOperations.TypedTuple<Object>) hang_spark_job_collection.toArray()[0];
        for (ZSetOperations.TypedTuple<Object> typedTuple : hang_spark_job_collection) {
            List<String> list = map.get(typedTuple.getScore());
            if (list != null) {
                list.add(String.valueOf(typedTuple.getValue()));
            } else {
                list = new ArrayList<>();
                list.add(String.valueOf(typedTuple.getValue()));
            }
            map.put(typedTuple.getScore(), list);
        }

        List<String> list = map.get(sparkJobInfo.getLevel());
        while (Objects.equals(jobId, list.get(0))) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果运行队列已满 等待20ms
        while (isFullRunningQueue()) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        //此处需要接redis锁

        if (!Objects.equals(tuple.getValue(), jobId)) {
            Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().rangeWithScores(RUNNING_SPARK_JOB_COLLECTION, level - 1, level);
            assert typedTuples != null;
            if (typedTuples.size() == LEVEL_RESOURCE_PROPORT[level - 1]) {
                typedTuples = redisTemplate.opsForZSet().rangeWithScores(RUNNING_SPARK_JOB_COLLECTION, 0, -1);

            }
        }

        //放行，将任务从等待队列中删除
        removeWaitJob(jobId);
    }
}
