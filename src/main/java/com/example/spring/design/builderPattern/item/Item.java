package com.example.spring.design.builderPattern.item;


import com.example.spring.design.builderPattern.packing.Packing;

/**
 * @author: Staro
 * @date: 2019/11/23 14:25
 * @Description:
 */
public interface Item {
    public String name();
    public Packing packing();
    public double price();
}
