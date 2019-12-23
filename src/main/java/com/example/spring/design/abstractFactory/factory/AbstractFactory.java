package com.example.spring.design.abstractFactory.factory;

import com.example.spring.design.abstractFactory.annotation.TypeFactoryMethod;
import com.example.spring.design.abstractFactory.annotation.TypeModel;
import org.reflections.Reflections;

import java.util.Set;

/**
 * @author: Staro
 * @date: 2019/11/22 10:00
 * @Description:
 */
public interface AbstractFactory {
    String path = "com.cache.radis.demo.design.abstractFactory.model.impl";
    Set<Class<?>> classes = new Reflections(path).getTypesAnnotatedWith(TypeModel.class);
//    @TypeFactoryMethod("color")
//    Color getColor(String colorType);
//    @TypeFactoryMethod("shape")
//    Shape getShape(String shapeType);
    @TypeFactoryMethod("execute")
    void execute(String shapeTypeOrColorType, String methodNo);

}
