package com.example.spring.design.Bridge.shape;


import com.example.spring.design.Bridge.circle.DrawAPI;

/**
 * @author: Staro
 * @date: 2019/11/29 17:20
 * @Description:
 */
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
