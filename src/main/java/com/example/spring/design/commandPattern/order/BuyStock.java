package com.example.spring.design.commandPattern.order;


import com.example.spring.design.commandPattern.req.Stock;

/**
 * @author: Staro
 * @date: 2019/12/7 16:20
 * @Description:
 */
public class BuyStock implements Order {
    private Stock stock;

    public BuyStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }
}
