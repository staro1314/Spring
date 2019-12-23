package com.example.spring.design.facadePattern;


import com.example.spring.design.facadePattern.facade.ShapeMaker;

/**
 * @author: Staro
 * @date: 2019/12/5 17:19
 * @Description: 外观模式
 */
public class FacadeDomain {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
