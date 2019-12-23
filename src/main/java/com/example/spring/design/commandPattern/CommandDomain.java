package com.example.spring.design.commandPattern;

import com.example.spring.design.commandPattern.invoker.Broker;
import com.example.spring.design.commandPattern.order.BuyStock;
import com.example.spring.design.commandPattern.order.SellStock;
import com.example.spring.design.commandPattern.req.Stock;

/**
 * @author: Staro
 * @date: 2019/12/7 16:28
 * @Description: 命令模式
 */
public class CommandDomain {
    public static void main(String[] args) {
        Stock stock=new Stock();
        BuyStock buyStock=new BuyStock(stock);
        SellStock sellStock=new SellStock(stock);
        Broker broker=new Broker();
        broker.takeOrder(buyStock);
        broker.takeOrder(sellStock);
        broker.placeOrder();
    }
}
