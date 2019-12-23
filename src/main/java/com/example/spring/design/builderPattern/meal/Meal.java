package com.example.spring.design.builderPattern.meal;


import com.example.spring.design.builderPattern.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/11/23 14:38
 * @Description:
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    void addItem(Item item) {
        if (item != null)
            items.add(item);
    }

    private double getPrice() {
        return items.stream().mapToDouble(Item::price).sum();
    }

    public void showItem() {
        items.forEach(item -> {
            System.out.print("Item : " + item.name());
            System.out.print(", Packing : " + item.packing().pack());
            System.out.println(", Price : " + item.price());
        });
        System.out.println("Total Price:" + getPrice());
    }
}
