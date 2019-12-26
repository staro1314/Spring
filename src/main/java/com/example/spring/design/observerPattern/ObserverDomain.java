package com.example.spring.design.observerPattern;

import com.example.spring.design.observerPattern.observer.BinaryObserver;
import com.example.spring.design.observerPattern.observer.HexaObserver;
import com.example.spring.design.observerPattern.observer.OctalObserver;
import com.example.spring.design.observerPattern.sub.Subject;

/**
 * @author: Staro
 * @date: 2019/12/26 10:01
 * @Description: 观察者模式
 */
public class ObserverDomain {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change 15");
        subject.setState(15);
        System.out.println("----------------");
        System.out.println("Second state change 10");
        subject.setState(10);
    }
}
