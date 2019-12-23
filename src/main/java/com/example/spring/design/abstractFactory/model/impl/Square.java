package com.example.spring.design.abstractFactory.model.impl;

import com.example.spring.design.abstractFactory.annotation.TypeModel;
import com.example.spring.design.abstractFactory.model.Shape;

/**
 * @author: Staro
 * @date: 2019/11/21 17:19
 * @Description:
 */
@TypeModel("square")
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square");
    }
}
