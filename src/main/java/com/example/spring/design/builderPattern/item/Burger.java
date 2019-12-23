package com.example.spring.design.builderPattern.item;


import com.example.spring.design.builderPattern.packing.Packing;
import com.example.spring.design.builderPattern.packing.impl.Wrapper;

/**
 * @author: Staro
 * @date: 2019/11/23 14:32
 * @Description:
 */
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }
}
