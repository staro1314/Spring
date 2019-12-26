package com.example.spring.design.nullObjectPattern;

/**
 * @author: Staro
 * @date: 2019/12/26 11:23
 * @Description:
 */
public class NullCustomer extends AbstractCustomer {
    public NullCustomer(String name) {
        this.name = name;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }
}
