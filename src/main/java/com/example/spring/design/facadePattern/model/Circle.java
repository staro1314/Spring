package com.example.spring.design.facadePattern.model;

/**
 * @author: Staro
 * @date: 2019/12/5 17:14
 * @Description:
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
