package com.example.spring.design.observerPattern.observer;

import com.example.spring.design.observerPattern.sub.Subject;

/**
 * @author: Staro
 * @date: 2019/12/26 9:47
 * @Description:
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject=subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "+
        Integer.toOctalString(subject.getState()));
    }
}
