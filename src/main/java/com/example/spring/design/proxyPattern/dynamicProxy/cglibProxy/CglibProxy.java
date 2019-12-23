package com.example.spring.design.proxyPattern.dynamicProxy.cglibProxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: Staro
 * @date: 2019/12/7 10:46
 * @Description:
 */
public class CglibProxy implements MethodInterceptor {
    private Object target;

    public Object getInstance(final Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("preparing before buying");
        Object invoke = method.invoke(target, objects);
        System.out.println("preparing after buying");
        return invoke;
    }
}
