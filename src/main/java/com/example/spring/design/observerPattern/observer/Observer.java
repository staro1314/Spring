package com.example.spring.design.observerPattern.observer;

import com.example.spring.design.observerPattern.sub.Subject;

/**
 * @author: Staro
 * @date: 2019/12/26 9:41
 * @Description:
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
