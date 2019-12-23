package com.example.spring.design.prototypePattern.model.impl;


import com.example.spring.design.prototypePattern.model.Shape;

/**
 * @author: Staro
 * @date: 2019/11/23 17:17
 * @Description:
 */
public class Circle extends Shape {
    public Circle() {
        type="Circle";
    }

    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
