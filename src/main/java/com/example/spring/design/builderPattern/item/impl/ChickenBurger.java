package com.example.spring.design.builderPattern.item.impl;


import com.example.spring.design.builderPattern.item.Burger;

/**
 * @author: Staro
 * @date: 2019/11/23 14:35
 * @Description:
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public double price() {
        return 50.5;
    }
}
