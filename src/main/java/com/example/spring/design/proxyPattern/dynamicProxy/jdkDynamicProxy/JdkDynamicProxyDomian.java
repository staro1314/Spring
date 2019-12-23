package com.example.spring.design.proxyPattern.dynamicProxy.jdkDynamicProxy;


import com.example.spring.design.proxyPattern.model.BuyHouse;
import com.example.spring.design.proxyPattern.model.BuyHouseImpl;

import java.lang.reflect.Proxy;

/**
 * @author: Staro
 * @date: 2019/12/6 11:52
 * @Description: JdkDynamic代理
 */
public class JdkDynamicProxyDomian {
    public static void main(String[] args) {
        BuyHouse buyHouse=new BuyHouseImpl();
        BuyHouse proxyHouse = (BuyHouse) Proxy.newProxyInstance(
                BuyHouse.class.getClassLoader(),
                new Class[]{BuyHouse.class},
                new JdkDynamicProxyHandler(buyHouse));
        proxyHouse.buyHouse();
    }
}
