package com.example.spring.design.proxyPattern.staticProxy;


import com.example.spring.design.proxyPattern.model.BuyHouse;

/**
 * @author: Staro
 * @date: 2019/12/6 11:16
 * @Description:
 */
public class HouseStaticProxy implements BuyHouse {
    private BuyHouse buyHouse;

    public HouseStaticProxy(final BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHouse() {
        System.out.println("Preparing before buying");
        buyHouse.buyHouse();
        System.out.println("Preparing after buying");
    }
}
