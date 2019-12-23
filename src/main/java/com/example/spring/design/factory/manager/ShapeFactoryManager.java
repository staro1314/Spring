package com.example.spring.design.factory.manager;

import com.example.spring.design.factory.annotation.ShapeFactory;
import com.example.spring.design.factory.annotation.ShapeMethod;
import com.example.spring.design.factory.model.Shape;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Staro
 * @date: 2019/11/21 17:33
 * @Description: 工厂模式
 */
@Slf4j
@Component
public class ShapeFactoryManager {
    private static Map<String, Class> shapeSubclass = new HashMap<>();
    private static Map<String, Method> shapeMethod = new HashMap<>();
    private static String path = "com.cache.radis.demo.design.factory.model.impl";

    static {
        Set<Class<?>> annotatedWith = new Reflections(path).getTypesAnnotatedWith(ShapeFactory.class);
        for (Class cls : annotatedWith) {
            if (Shape.class.isAssignableFrom(cls)) {
                shapeSubclass.put(((ShapeFactory) cls.getAnnotation(ShapeFactory.class)).value().toLowerCase(), cls);
            }
        }
        Class cls=Shape.class;
        Method[] methods = cls.getMethods();
        for (Method method:methods){
            ShapeMethod annotation = method.getAnnotation(ShapeMethod.class);
            if (annotation !=null){
                shapeMethod.put(annotation.value(),method);
            }
        }
    }

    public static void executeMethod(String shapeName,String methodNo){
        try {
            Class aClass = shapeSubclass.get(shapeName);
            Shape shape= (Shape) aClass.newInstance();
            Method method = shapeMethod.get(methodNo.toLowerCase());
            if (method!=null){
                method.invoke(shape);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        executeMethod("circle","draw");
    }
}
