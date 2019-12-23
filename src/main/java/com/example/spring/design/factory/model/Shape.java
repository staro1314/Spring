package com.example.spring.design.factory.model;


import com.example.spring.design.factory.annotation.ShapeMethod;

/**
 * @author: Staro
 * @date: 2019/11/21 17:18
 * @Description:
 */
public interface Shape {
    @ShapeMethod("draw")
    void draw();
}
