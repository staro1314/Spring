package com.example.spring.design.serviceLoader;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: Staro
 * @date: 2020/6/16 16:12
 * @Description:
 */
public class TestServiceLoader {
    public static void main(String[] args) {
        ServiceLoader<IMyServiceLoader> serviceLoaders = ServiceLoader.load(IMyServiceLoader.class);
        Iterator<IMyServiceLoader> iterator = serviceLoaders.iterator();
        while (iterator.hasNext()){
            IMyServiceLoader myServiceLoader = iterator.next();
            System.out.println(myServiceLoader.getName()+": "+myServiceLoader.sayHello());
        }
    }
}
