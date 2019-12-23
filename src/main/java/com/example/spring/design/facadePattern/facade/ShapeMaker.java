package com.example.spring.design.facadePattern.facade;


import com.example.spring.design.facadePattern.model.Circle;
import com.example.spring.design.facadePattern.model.Rectangle;
import com.example.spring.design.facadePattern.model.Shape;
import com.example.spring.design.facadePattern.model.Square;

/**
 * @author: Staro
 * @date: 2019/12/5 17:14
 * @Description:
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
