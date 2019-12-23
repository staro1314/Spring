package com.example.spring.design.abstractFactory.model;


import com.example.spring.design.abstractFactory.annotation.TypeColorMethod;

/**
 * @author: Staro
 * @date: 2019/11/22 9:56
 * @Description:
 */
public interface Color {
    @TypeColorMethod("color")
    void fill();
}
