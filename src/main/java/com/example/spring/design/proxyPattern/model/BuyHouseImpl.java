package com.example.spring.design.proxyPattern.model;

/**
 * @author: Staro
 * @date: 2019/12/6 11:15
 * @Description:
 */
public class BuyHouseImpl implements BuyHouse {
    @Override
    public void buyHouse() {
        System.out.println("I want to buy a house");
    }
}
