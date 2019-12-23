package com.example.spring.design.proxyPattern.staticProxy;


import com.example.spring.design.proxyPattern.model.BuyHouse;
import com.example.spring.design.proxyPattern.model.BuyHouseImpl;

/**
 * @author: Staro
 * @date: 2019/12/6 11:19
 * @Description: 静态代理
 */
public class StaticProxyDomain {
    public static void main(String[] args) {
        BuyHouse buyHouse=new BuyHouseImpl();
        buyHouse.buyHouse();
        BuyHouse proxyBuyHouse=new HouseStaticProxy(buyHouse);
        proxyBuyHouse.buyHouse();
    }
}
