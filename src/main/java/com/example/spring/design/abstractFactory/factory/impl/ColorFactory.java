package com.example.spring.design.abstractFactory.factory.impl;

import com.example.spring.design.abstractFactory.annotation.TypeColorMethod;
import com.example.spring.design.abstractFactory.annotation.TypeFactory;
import com.example.spring.design.abstractFactory.annotation.TypeModel;
import com.example.spring.design.abstractFactory.factory.AbstractFactory;
import com.example.spring.design.abstractFactory.model.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Staro
 * @date: 2019/11/22 10:01
 * @Description:
 */
//@Component
@TypeFactory("colorFactory")
public class ColorFactory implements AbstractFactory {

    private static Map<String, Class> colorCls = new HashMap<>();
    private static Map<String, Method> colorMethod = new HashMap<>();
//    private static String path = "com.cache.radis.demo.design.abstractFactory.model.impl";

    static {
//        Set<Class<?>> classes = new Reflections(path).getTypesAnnotatedWith(TypeModel.class);
        for (Class cls : classes) {
            if (Color.class.isAssignableFrom(cls)) {
                colorCls.put(((TypeModel) cls.getAnnotation(TypeModel.class)).value(), cls);
            }
        }
        Method[] methods = Color.class.getMethods();
        for (Method method : methods) {
            TypeColorMethod annotation = method.getAnnotation(TypeColorMethod.class);
            if (annotation != null) {
                colorMethod.put(annotation.value(), method);
            }
        }
    }

//    @Override
    private Color getColor(String colorType) {
        try {
            Class cls = colorCls.get(colorType.toLowerCase());
            return (Color) cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public Shape getShape(String shapeType) {
//        return null;
//    }

    @Override
    public void execute(String shapeTypeOrColorType, String methodNo) {
        try {
            Color color = getColor(shapeTypeOrColorType);
            Method method = colorMethod.get(methodNo.toLowerCase());
            method.invoke(color);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
