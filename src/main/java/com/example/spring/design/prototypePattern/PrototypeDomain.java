package com.example.spring.design.prototypePattern;


import com.example.spring.design.prototypePattern.model.ShapeCache;

/**
 * @author: Staro
 * @date: 2019/11/23 17:10
 * @Description: 原型模式
 */
public class PrototypeDomain {
    public static void main(String[] args) {
        ShapeCache.loadCache();
        ShapeCache.getShape("1").draw();
        ShapeCache.getShape("2").draw();
        ShapeCache.getShape("3").draw();
    }
}
