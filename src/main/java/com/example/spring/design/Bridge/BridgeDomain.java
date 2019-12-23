package com.example.spring.design.Bridge;


import com.example.spring.design.Bridge.circle.impl.GreenCircle;
import com.example.spring.design.Bridge.circle.impl.RedCircle;
import com.example.spring.design.Bridge.shape.Circle;
import com.example.spring.design.Bridge.shape.Shape;

/**
 * @author: Staro
 * @date: 2019/11/29 17:23
 * @Description:
 */
public class BridgeDomain {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
