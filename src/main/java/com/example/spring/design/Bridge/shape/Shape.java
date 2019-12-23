package com.example.spring.design.Bridge.shape;


import com.example.spring.design.Bridge.circle.DrawAPI;

/**
 * @author: Staro
 * @date: 2019/11/29 17:18
 * @Description:
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    public Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
