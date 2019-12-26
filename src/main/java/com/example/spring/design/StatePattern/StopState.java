package com.example.spring.design.StatePattern;

/**
 * @author: Staro
 * @date: 2019/12/26 10:20
 * @Description:
 */
public class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}
