package com.example.spring.design.Bridge.circle.impl;


import com.example.spring.design.Bridge.circle.DrawAPI;

/**
 * @author: Staro
 * @date: 2019/11/29 17:16
 * @Description:
 */
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
