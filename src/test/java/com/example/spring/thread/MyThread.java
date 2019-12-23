package com.example.spring.thread;

import com.example.spring.domain.bean.UserBean;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

/**
 * @author: Staro
 * @date: 2019/5/3116:59
 * @Description:
 */
@SuppressWarnings("Duplicates")
public class MyThread extends Thread{
    private List<String> list;
    private ValueOperations<String, Object> stringOperations;
    private String tag;

    public MyThread(List<String> list, ValueOperations<String, Object> stringOperations, String tag) {
        this.list = list;
        this.stringOperations = stringOperations;
        this.tag=tag;
    }

    @Override
    public void run() {
        readRedis(list,stringOperations,tag);
    }

    public static void readRedis(List<String> list, ValueOperations<String, Object> stringOperations, String tag) {
        System.out.println("***********************************"+tag+"-读取*********************************************");
        long start = System.currentTimeMillis();
        int i = 1;
        for (String key : list) {
            UserBean user = (UserBean) stringOperations.get(key);
            i++;
            if (i % 1000000 == 0) {
                long end = System.currentTimeMillis();
                System.out.println(tag+"处理数据中  ==> 读取数据总条数 size : "
                        + i + " total use: " + (end - start) / 1000 + " 处理速度：" + i / ((end - start) / 1000) + "条/s");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(tag+":complete total-spend-time:" + (end - start) / 1000);
    }

}
