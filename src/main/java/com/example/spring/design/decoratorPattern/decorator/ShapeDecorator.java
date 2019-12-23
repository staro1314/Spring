package com.example.spring.design.decoratorPattern.decorator;


import com.example.spring.design.decoratorPattern.model.Shape;

/**
 * @author: Staro
 * @date: 2019/12/5 15:53
 * @Description:
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decorator;

    public ShapeDecorator(Shape decorator) {
        this.decorator = decorator;
    }

    @Override
    public void draw() {
        decorator.draw();
    }
}
