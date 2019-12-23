package com.example.spring.design.commandPattern.order;


import com.example.spring.design.commandPattern.req.Stock;

/**
 * @author: Staro
 * @date: 2019/12/7 16:22
 * @Description:
 */
public class SellStock implements Order {
    private Stock stock;

    public SellStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}
