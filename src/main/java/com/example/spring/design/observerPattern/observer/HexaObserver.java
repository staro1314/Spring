package com.example.spring.design.observerPattern.observer;

import com.example.spring.design.observerPattern.sub.Subject;

/**
 * @author: Staro
 * @date: 2019/12/26 9:47
 * @Description:
 */
public class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject=subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "+
        Integer.toHexString(subject.getState()));
    }
}
