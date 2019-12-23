package com.example.spring.design.prototypePattern.model.impl;


import com.example.spring.design.prototypePattern.model.Shape;

/**
 * @author: Staro
 * @date: 2019/11/23 17:11
 * @Description:
 */
public class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}
