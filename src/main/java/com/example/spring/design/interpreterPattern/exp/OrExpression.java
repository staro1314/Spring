package com.example.spring.design.interpreterPattern.exp;

/**
 * @author: Staro
 * @date: 2019/12/7 16:56
 * @Description:
 */
public class OrExpression implements Expression {
    private Expression exp1;
    private Expression exp2;

    public OrExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public boolean interpret(String context) {
        return exp1.interpret(context) || exp2.interpret(context);
    }
}
