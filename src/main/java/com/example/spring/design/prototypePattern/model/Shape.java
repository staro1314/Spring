package com.example.spring.design.prototypePattern.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Staro
 * @date: 2019/11/23 17:06
 * @Description:
 */
@Getter
@Setter
public abstract class Shape implements Cloneable {
    protected String id;
    protected String type;

    public abstract void draw();

    @Override
    protected Shape clone() {
        Shape shape = null;
        try {
            shape = (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shape;
    }
}
