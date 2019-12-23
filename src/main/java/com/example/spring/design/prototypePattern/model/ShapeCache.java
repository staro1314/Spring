package com.example.spring.design.prototypePattern.model;

import com.example.spring.design.prototypePattern.model.impl.Circle;
import com.example.spring.design.prototypePattern.model.impl.Rectangle;
import com.example.spring.design.prototypePattern.model.impl.Square;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Staro
 * @date: 2019/11/23 17:19
 * @Description:
 */
public class ShapeCache {
    private static Map<String, Shape> shapeCache = new HashMap<>();

    public static Shape getShape(String shapeId) {
        Shape shape = shapeCache.get(shapeId);
        return shape.clone();
    }

    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeCache.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeCache.put(rectangle.getId(), rectangle);

        Square square = new Square();
        square.setId("3");
        shapeCache.put(square.getId(), square);
    }
}
