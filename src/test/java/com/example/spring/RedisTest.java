package com.example.spring;

import com.example.spring.domain.bean.UserBean;
import com.example.spring.thread.MyThread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: Staro
 * @date: 2019/5/3011:28
 * @Description:
 */
@SuppressWarnings("Duplicates")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedis() {

        List<String> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        ValueOperations<String, Object> stringOperations = redisTemplate.opsForValue();
        UserBean userBean = new UserBean();
        userBean.setId("1");
        userBean.setUsername("redis");
        userBean.setSex("男");
        System.out.println("***********************************插入********************************************");
        //插入
        for (int i = 1; i <= 50000000; i++) {
//            String key = UUID.randomUUID().toString();
            Map<String, Object> map = new HashMap<>();
            map.put("" + i, userBean);
            stringOperations.multiSet(map);
//            stringOperations.set("" + i, userBean);
            if (i % 1000000 == 0) {
                long end = System.currentTimeMillis();
                System.out.println("处理数据中  ==> 插入数据总条数 size : "
                        + i + " total use: " + (end - start) / 1000 + " 处理速度：" + i / ((end - start) / 1000) + "条/s");
            }
            list.add("" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("total-spend-time:" + (end - start) / 1000);

        readRedis(list, stringOperations);

        System.out.println("***********************************删除*********************************************");
//        for (String key : list) {
//            redisTemplate.delete(key);
//        }
    }

    @Test
    public void testMultiRedis() {

        List<String> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        ValueOperations<String, Object> stringOperations = redisTemplate.opsForValue();
        UserBean userBean = new UserBean();
        userBean.setId("1");
        userBean.setUsername("redis");
        userBean.setSex("男");
        System.out.println("***********************************插入********************************************");
        //插入
        int multi = 100000;
        for (int i = 1; i <= 50000000 / multi; i++) {
            Map<String, UserBean> map = new HashMap<>();
            for (int j = 0; j < multi; j++) {
                String key = UUID.randomUUID().toString();
                map.put(key, userBean);
                list.add(key);
            }
            stringOperations.multiSet(map);
            if (i % (100000 / multi) == 0) {
                long end = System.currentTimeMillis();
                System.out.println("处理数据中  ==> 插入数据总条数 size : "
                        + i * multi + " total use: " + (end - start) / 1000 + " 处理速度：" + i * multi / ((end - start) / 1000) + "条/s");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("total-spend-time:" + (end - start) / 1000);

        System.out.println("***********************************读取*********************************************");
        start = System.currentTimeMillis();
        for (int i = 0; i < list.size() / multi; i++) {
            List<String> l = new ArrayList();
            for (int j = i * multi; j < (i + 1) * multi; j++) {
                l.add(list.get(j));
            }
            List<Object> userBeans = stringOperations.multiGet(l);
            if ((i + 1) % (100000 / multi) == 0) {
                end = System.currentTimeMillis();
                System.out.println("处理数据中  ==> 读取数据总条数 size : "
                        + (i + 1) * multi + " total use: " + (end - start) / 1000 + " 处理速度：" + ((i + 1) * multi / (end - start)) * 1000 + "条/s");
            }
        }
        end = System.currentTimeMillis();
        System.out.println("total-spend-time:" + (end - start) / 1000);

        System.out.println("***********************************删除*********************************************");
//        for (String key : list) {
//            redisTemplate.delete(key);
//        }
    }

    public void readRedis(List<String> list, ValueOperations<String, Object> stringOperations) {
        System.out.println("***********************************读取*********************************************");
        long start = System.currentTimeMillis();
        int i = 1;
        for (String key : list) {
            UserBean user = (UserBean) stringOperations.get(key);
            i++;
            if (i % 1000000 == 0) {
                long end = System.currentTimeMillis();
                System.out.println("处理数据中  ==> 读取数据总条数 size : "
                        + i + " total use: " + (end - start) / 1000 + " 处理速度：" + i / ((end - start) / 1000) + "条/s");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("total-spend-time:" + (end - start) / 1000);
    }


    @Test
    public void testRedisRunnable() throws InterruptedException {

        List<String> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        ValueOperations<String, Object> stringOperations = redisTemplate.opsForValue();
        UserBean userBean = new UserBean();
        userBean.setId("1");
        userBean.setUsername("redis");
        userBean.setSex("男");
        System.out.println("***********************************插入********************************************");
        //插入
        int multi = 10000;
        for (int i = 1; i <= 10000000 / multi; i++) {
            Map<String, UserBean> map = new HashMap<>();
            for (int j = 0; j < multi; j++) {
                String key = UUID.randomUUID().toString();
                map.put(key, userBean);
                list.add(key);
            }
            stringOperations.multiSet(map);
            if (i % (100000 / multi) == 0) {
                long end = System.currentTimeMillis();
                System.out.println("处理数据中  ==> 插入数据总条数 size : "
                        + i * multi + " total use: " + (end - start) / 1000 + " 处理速度：" + i * multi / ((end - start) / 1000) + "条/s");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("total-spend-time:" + (end - start) / 1000);

        int index = list.size();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            if (i < index / 5) {
                list1.add(list.get(i));
            }
            if (i >= index / 5 && i < index * 2 / 5) {
                list2.add(list.get(i));
            }
            if (i >= index * 2 / 5 && i < index * 3 / 5) {
                list3.add(list.get(i));
            }
            if (i >= index * 3 / 5 && i < index * 4 / 5) {
                list4.add(list.get(i));
            }
            if (i >= index * 4 / 5) {
                list5.add(list.get(i));
            }
        }
        MyThread myThread1 = new MyThread(list1, stringOperations, "MyThread-01");
        MyThread myThread2 = new MyThread(list2, stringOperations, "MyThread-02");
        MyThread myThread3 = new MyThread(list3, stringOperations, "MyThread-03");
        MyThread myThread4 = new MyThread(list4, stringOperations, "MyThread-04");
        MyThread myThread5 = new MyThread(list5, stringOperations, "MyThread-05");
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        Thread.sleep(1000 * 60 * 5);

    }


    @Test
    public void testRedis_01(){
        ValueOperations<String, Object> stringOperations = redisTemplate.opsForValue();
        stringOperations.set("aa","redis22");
        System.out.println(stringOperations.get("aa"));
    }
}
