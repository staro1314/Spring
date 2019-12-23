package com.example.spring.design.abstractFactory.factory.impl;

import com.example.spring.design.abstractFactory.annotation.TypeFactory;
import com.example.spring.design.abstractFactory.annotation.TypeModel;
import com.example.spring.design.abstractFactory.annotation.TypeShapeMethod;
import com.example.spring.design.abstractFactory.factory.AbstractFactory;
import com.example.spring.design.abstractFactory.model.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Staro
 * @date: 2019/11/22 10:00
 * @Description:
 */

//@Component
@TypeFactory("shapeFactory")
public class ShapeFactory implements AbstractFactory {

    private static Map<String, Class> shapeCls = new HashMap<>();
    private static Map<String, Method> shapeMethod = new HashMap<>();
//    private static String path = "com.cache.radis.demo.design.abstractFactory.model.impl";

    static {
//        Set<Class<?>> classes = new Reflections(path).getTypesAnnotatedWith(TypeShape.class);
        for (Class c : classes) {
            if (Shape.class.isAssignableFrom(c)) {
                shapeCls.put(((TypeModel) c.getAnnotation(TypeModel.class)).value(), c);
            }
        }
        Method[] methods = Shape.class.getMethods();
        for (Method method : methods) {
            TypeShapeMethod annotation = method.getAnnotation(TypeShapeMethod.class);
            if (annotation != null) {
                shapeMethod.put(annotation.value(), method);
            }
        }
    }

    //    @Override
    private Shape getShape(String shapeType) {
        try {
            Class cls = shapeCls.get(shapeType.toLowerCase());
            return (Shape) cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void execute(String shapeTypeOrColorType, String methodNo) {
        try {
            Shape shape = getShape(shapeTypeOrColorType);
            Method method = shapeMethod.get(methodNo);
            method.invoke(shape);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Color getColor(String colorType) {
//        return null;
//    }
}
