package com.example.spring.design.interpreterPattern.exp;

/**
 * @author: Staro
 * @date: 2019/12/7 16:54
 * @Description:
 */
public interface Expression {
    public boolean interpret(String context);
}
