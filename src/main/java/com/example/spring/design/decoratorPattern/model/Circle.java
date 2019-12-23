package com.example.spring.design.decoratorPattern.model;

/**
 * @author: Staro
 * @date: 2019/12/5 15:52
 * @Description:
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
