package com.example.spring.design.factory.model.impl;


import com.example.spring.design.factory.annotation.ShapeFactory;
import com.example.spring.design.factory.model.Shape;

/**
 * @author: Staro
 * @date: 2019/11/21 17:19
 * @Description:
 */
@ShapeFactory("rectangle")
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}
