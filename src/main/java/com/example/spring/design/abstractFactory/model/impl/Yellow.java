package com.example.spring.design.abstractFactory.model.impl;


import com.example.spring.design.abstractFactory.annotation.TypeModel;
import com.example.spring.design.abstractFactory.model.Color;

/**
 * @author: Staro
 * @date: 2019/11/22 9:58
 * @Description:
 */
@TypeModel("yellow")
public class Yellow implements Color {
    @Override
    public void fill() {
        System.out.println("Yellow");
    }
}
