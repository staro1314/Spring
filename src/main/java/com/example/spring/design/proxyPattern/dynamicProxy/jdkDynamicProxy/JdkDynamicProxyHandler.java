package com.example.spring.design.proxyPattern.dynamicProxy.jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: Staro
 * @date: 2019/12/6 11:23
 * @Description:
 */
public class JdkDynamicProxyHandler implements InvocationHandler {

    private Object target;

    public JdkDynamicProxyHandler(final Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Preparing before buying");
        Object result = method.invoke(target, args);
        System.out.println("Preparing after buying");
        return result;
    }
}
