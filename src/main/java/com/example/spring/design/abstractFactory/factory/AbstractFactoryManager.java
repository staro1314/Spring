package com.example.spring.design.abstractFactory.factory;

import com.example.spring.design.abstractFactory.annotation.TypeFactory;
import com.example.spring.design.abstractFactory.annotation.TypeFactoryMethod;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Staro
 * @date: 2019/11/22 16:45
 * @Description: 抽象工厂模式
 */
@Slf4j
public class AbstractFactoryManager {

    private static Map<String, Class> factoryCls = new HashMap<>();
    private static Map<String, Method> factoryMethod = new HashMap<>();
    private static String path = "com.cache.radis.demo.design.abstractFactory.factory.impl";

    static {
        Set<Class<?>> classes = new Reflections(path).getTypesAnnotatedWith(TypeFactory.class);
        for (Class cls : classes) {
            if (AbstractFactory.class.isAssignableFrom(cls)) {
                factoryCls.put(((TypeFactory) cls.getAnnotation(TypeFactory.class)).value(), cls);
            }
        }
        Class cls = AbstractFactory.class;
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            TypeFactoryMethod annotation = method.getAnnotation(TypeFactoryMethod.class);
            if (annotation != null)
                factoryMethod.put(annotation.value(), method);
        }
    }

    public static void execute(String factoryType,String shapeOrColor,String shapeTypeOrColorType,String methodNo){
        try {
            Class cls = factoryCls.get(factoryType);
            AbstractFactory factory= (AbstractFactory) cls.newInstance();
            Method method = factoryMethod.get(shapeOrColor);
            method.invoke(factory,shapeTypeOrColorType,methodNo);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        execute("shapeFactory","execute","circle","draw");
        execute("colorFactory","execute","green","color");
    }
}
