package com.example.spring.design.StatePattern;

/**
 * @author: Staro
 * @date: 2019/12/26 10:32
 * @Description: 状态模式
 */
public class StatePatternDomain {
    public static void main(String[] args) {
        Context context=new Context();
        State state=new StartState();
        state.doAction(context);
        System.out.println(state.toString());

        state=new StopState();
        state.doAction(context);
        System.out.println(state.toString());
    }
}
