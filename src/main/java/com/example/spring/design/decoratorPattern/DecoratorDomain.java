package com.example.spring.design.decoratorPattern;


import com.example.spring.design.decoratorPattern.decorator.RedShapeDecorator;
import com.example.spring.design.decoratorPattern.decorator.ShapeDecorator;
import com.example.spring.design.decoratorPattern.model.Circle;
import com.example.spring.design.decoratorPattern.model.Rectangle;
import com.example.spring.design.decoratorPattern.model.Shape;

/**
 * @author: Staro
 * @date: 2019/12/5 15:59
 * @Description: 装饰器模式
 */
public class DecoratorDomain {
    public static void main(String[] args) {
        Shape circle=new Circle();
        ShapeDecorator redCircle=new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle= new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();
        System.out.println("\nCircle of red border");
        redCircle.draw();
        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
