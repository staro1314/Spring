package com.example.spring.design.decoratorPattern.model;

/**
 * @author: Staro
 * @date: 2019/12/5 15:51
 * @Description:
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
