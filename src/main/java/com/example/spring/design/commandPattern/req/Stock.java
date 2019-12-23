package com.example.spring.design.commandPattern.req;

/**
 * @author: Staro
 * @date: 2019/12/7 16:20
 * @Description:
 */
public class Stock {
    private String name = "car";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [ Name: " + name + ", " +
                "Quantity: " + quantity + " ] bought");
    }

    public void sell() {
        System.out.println("Stock [ Name: " + name + "," +
                "Quantity: " + quantity + " ] sold");
    }
}
