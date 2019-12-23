package com.example.spring.design.builderPattern.item.impl;


import com.example.spring.design.builderPattern.item.ColdDrink;

/**
 * @author: Staro
 * @date: 2019/11/23 14:37
 * @Description:
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public double price() {
        return 10.0;
    }
}
