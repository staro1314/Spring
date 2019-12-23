package com.example.spring.design.facadePattern.model;

/**
 * @author: Staro
 * @date: 2019/12/5 17:13
 * @Description:
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
