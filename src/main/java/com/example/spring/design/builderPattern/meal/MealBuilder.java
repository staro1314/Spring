package com.example.spring.design.builderPattern.meal;


import com.example.spring.design.builderPattern.item.impl.ChickenBurger;
import com.example.spring.design.builderPattern.item.impl.Coke;
import com.example.spring.design.builderPattern.item.impl.Pepsi;
import com.example.spring.design.builderPattern.item.impl.VegBurger;

/**
 * @author: Staro
 * @date: 2019/11/23 14:47
 * @Description:
 */
public class MealBuilder {

    public static Meal prepareVegMeal(){
        Meal meal=new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public static Meal prepareNoVegMeal(){
        Meal meal=new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
