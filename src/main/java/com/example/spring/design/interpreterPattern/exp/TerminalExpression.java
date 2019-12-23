package com.example.spring.design.interpreterPattern.exp;

/**
 * @author: Staro
 * @date: 2019/12/7 16:55
 * @Description:
 */
public class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }

}
