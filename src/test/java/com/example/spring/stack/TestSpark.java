package com.example.spring.stack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Staro
 * @date: 2019/10/11 16:47
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpark {
    @Autowired
    private SparkJobPriorityAlgorithm redisLockTool;

    @Test
    public void testRedisLock(){
        SparkJobInfo sparkJobInfo=new SparkJobInfo();
        sparkJobInfo.setDatasetId("11111111");
        sparkJobInfo.setJobId("2");
        sparkJobInfo.setLevel(1);
        sparkJobInfo.setCreateTime(System.currentTimeMillis());
        redisLockTool.doAroundJob_v2(sparkJobInfo);
//        sparkJobInfo.setDatasetId("22222");
//        sparkJobInfo.setJobId("2");
//        sparkJobInfo.setLevel(5);
//        sparkJobInfo.setCreateTime(System.currentTimeMillis());
//        redisLockTool.doAroundJob(sparkJobInfo);
//        sparkJobInfo.setDatasetId("33333");
//        sparkJobInfo.setJobId("2");
//        sparkJobInfo.setLevel(4);
//        sparkJobInfo.setCreateTime(System.currentTimeMillis());
//        redisLockTool.doAroundJob(sparkJobInfo);
//        sparkJobInfo.setDatasetId("44444");
//        sparkJobInfo.setJobId("2");
//        sparkJobInfo.setLevel(2);
//        sparkJobInfo.setCreateTime(System.currentTimeMillis());
//        redisLockTool.doAroundJob(sparkJobInfo);


    }
}
