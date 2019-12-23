package com.example.spring.design.abstractFactory.model;


import com.example.spring.design.abstractFactory.annotation.TypeShapeMethod;

/**
 * @author: Staro
 * @date: 2019/11/22 9:53
 * @Description:
 */
public interface Shape {
    @TypeShapeMethod("draw")
    void draw();
}
