package com.example.spring.cachebuilder;

import com.google.common.cache.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author: Staro
 * @date: 2020/5/27 11:40
 * @Description:
 */
@SpringBootTest
public class CacheBuilderTest {
    public static LoadingCache<Integer, Student> studentCache = null;

    @Before
    public void initCache() {
        studentCache = CacheBuilder
                .newBuilder()
                .concurrencyLevel(8)
                .expireAfterWrite(8, TimeUnit.SECONDS)
                .initialCapacity(10)
                .maximumSize(100)
                .recordStats()
                .removalListener(removalNotification -> System.out.println(removalNotification.getKey() + " was removed, cause is " + removalNotification.getCause()))
                .build(new CacheLoader<Integer, Student>() {
                    @Override
                    public Student load(Integer integer) throws Exception {
                        System.out.println("load student " + integer);
                        Student student = new Student();
                        student.setId(integer);
                        student.setName("name " + integer);
                        return student;
                    }
                });
    }

    @Test
    public void cacheBuilderTest(){
        IntStream.range(0,20).forEach(index->{
            try {
                Student student = studentCache.get(1);
                System.out.println(student);
                TimeUnit.SECONDS.sleep(1);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("cache stats: ");
        System.out.println(studentCache.stats().toString());
    }
}
