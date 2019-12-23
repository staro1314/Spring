package com.example.spring.design.builderPattern;


import com.example.spring.design.builderPattern.meal.Meal;
import com.example.spring.design.builderPattern.meal.MealBuilder;

/**
 * @author: Staro
 * @date: 2019/11/23 14:52
 * @Description: 创建者模式
 */
public class BuilderPatternDomain {
    public static void main(String[] args) {
        Meal meal = MealBuilder.prepareVegMeal();
        meal .showItem();
        System.out.println("------------------------------");
        meal=MealBuilder.prepareNoVegMeal();
        meal.showItem();
    }
}
