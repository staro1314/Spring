package com.example.spring.design.builderPattern.item.impl;


import com.example.spring.design.builderPattern.item.Burger;

/**
 * @author: Staro
 * @date: 2019/11/23 14:34
 * @Description:
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public double price() {
        return 25.0;
    }
}
