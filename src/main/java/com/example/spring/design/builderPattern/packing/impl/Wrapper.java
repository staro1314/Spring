package com.example.spring.design.builderPattern.packing.impl;


import com.example.spring.design.builderPattern.packing.Packing;

/**
 * @author: Staro
 * @date: 2019/11/23 14:28
 * @Description:
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
