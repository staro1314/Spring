package com.example.spring.design.commandPattern.invoker;


import com.example.spring.design.commandPattern.order.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/7 16:25
 * @Description:
 */
public class Broker {
    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrder() {
        orderList.forEach(Order::execute);
        orderList.clear();
    }
}
