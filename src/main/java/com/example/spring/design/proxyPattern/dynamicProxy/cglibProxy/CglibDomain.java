package com.example.spring.design.proxyPattern.dynamicProxy.cglibProxy;

import com.example.spring.design.proxyPattern.model.BuyHouse;
import com.example.spring.design.proxyPattern.model.BuyHouseImpl;

/**
 * @author: Staro
 * @date: 2019/12/7 10:56
 * @Description: CGLIB代理
 */
public class CglibDomain {
    public static void main(String[] args) {
        BuyHouse buyHouse=new BuyHouseImpl();
        CglibProxy cglibProxy=new CglibProxy();
        BuyHouse cglibBuyHouse = (BuyHouse) cglibProxy.getInstance(buyHouse);
        cglibBuyHouse.buyHouse();
    }
}
