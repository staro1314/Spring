package com.example.spring.design.nullObjectPattern;

/**
 * @author: Staro
 * @date: 2019/12/26 11:22
 * @Description:
 */
public class RealCustomer extends AbstractCustomer {
    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
