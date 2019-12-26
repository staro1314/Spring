package com.example.spring.design.observerPattern.observer;

import com.example.spring.design.observerPattern.sub.Subject;

/**
 * @author: Staro
 * @date: 2019/12/26 9:46
 * @Description:
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject=subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String:"+
        Integer.toBinaryString(subject.getState()));
    }
}
