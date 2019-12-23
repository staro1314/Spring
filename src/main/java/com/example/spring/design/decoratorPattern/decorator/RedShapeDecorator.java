package com.example.spring.design.decoratorPattern.decorator;


import com.example.spring.design.decoratorPattern.model.Shape;

/**
 * @author: Staro
 * @date: 2019/12/5 15:54
 * @Description:
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decorator) {
        super(decorator);
    }
    @Override
    public void draw() {
        decorator.draw();
        setRedBorder(decorator);
    }

    private void setRedBorder(Shape decorator){
        System.out.println("Border Color: Red");
    }
}
