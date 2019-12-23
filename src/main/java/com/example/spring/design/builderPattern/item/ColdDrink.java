package com.example.spring.design.builderPattern.item;


import com.example.spring.design.builderPattern.packing.Packing;
import com.example.spring.design.builderPattern.packing.impl.Bottle;

/**
 * @author: Staro
 * @date: 2019/11/23 14:33
 * @Description:
 */
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}
