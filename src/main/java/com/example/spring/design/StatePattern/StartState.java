package com.example.spring.design.StatePattern;

/**
 * @author: Staro
 * @date: 2019/12/26 10:19
 * @Description:
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}
