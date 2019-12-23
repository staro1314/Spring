package com.example.spring.design.abstractFactory.model.impl;


import com.example.spring.design.abstractFactory.annotation.TypeModel;
import com.example.spring.design.abstractFactory.model.Color;

/**
 * @author: Staro
 * @date: 2019/11/22 9:59
 * @Description:
 */
@TypeModel("green")
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Green");
    }
}
