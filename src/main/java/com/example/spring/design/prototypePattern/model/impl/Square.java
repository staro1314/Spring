package com.example.spring.design.prototypePattern.model.impl;


import com.example.spring.design.prototypePattern.model.Shape;

/**
 * @author: Staro
 * @date: 2019/11/23 17:16
 * @Description:
 */
public class Square extends Shape {
    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Square");
    }
}
