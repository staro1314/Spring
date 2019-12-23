package com.example.spring.design.facadePattern.model;

/**
 * @author: Staro
 * @date: 2019/12/5 17:13
 * @Description:
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
