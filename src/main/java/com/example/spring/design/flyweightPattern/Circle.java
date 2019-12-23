package com.example.spring.design.flyweightPattern;

import lombok.Setter;
import lombok.ToString;

/**
 * @author: Staro
 * @date: 2019/12/5 17:42
 * @Description:
 */
@Setter
@ToString
public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color
                + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}
