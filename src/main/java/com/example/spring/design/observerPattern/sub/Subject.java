package com.example.spring.design.observerPattern.sub;

import com.example.spring.design.observerPattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/26 9:41
 * @Description:
 */
public class Subject {
    private List<Observer> observers=new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObservers() {
        observers.forEach(Observer::update);
    }
}
