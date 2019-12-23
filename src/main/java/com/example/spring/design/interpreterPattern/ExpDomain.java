package com.example.spring.design.interpreterPattern;


import com.example.spring.design.interpreterPattern.exp.AndExpression;
import com.example.spring.design.interpreterPattern.exp.Expression;
import com.example.spring.design.interpreterPattern.exp.OrExpression;
import com.example.spring.design.interpreterPattern.exp.TerminalExpression;

/**
 * @author: Staro
 * @date: 2019/12/7 17:11
 * @Description: 解释器模式
 */
public class ExpDomain {
    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();
        System.out.println("John is male?:" + isMale.interpret("John"));
        System.out.println("Julie is a married women?" + isMarriedWoman.interpret("Married Julie"));
    }

    //规则：Robert 和 John 是男性
    private static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    private static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }
}
