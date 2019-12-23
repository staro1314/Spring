package com.example.spring.design.flyweightPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Staro
 * @date: 2019/12/5 17:44
 * @Description:
 */
public class ShapeFactory {
    private static final Map<String, Shape> map = new HashMap<>();

    public static Shape getCircle(String color) {
        Shape shape = map.get(color);
        if (shape == null) {
            shape = new Circle(color);
            map.put(color, shape);
            System.out.println("Creating circle of color : " + color);
        }
        return shape;
    }
}
