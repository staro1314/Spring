package com.example.spring.design.mementoPattern;

/**
 * @author: Staro
 * @date: 2019/12/26 9:19
 * @Description: 备忘录模式
 */
public class MementoDomain {
    public static void main(String[] args) {
        CareTaker careTaker=new CareTaker();
        Originator originator=new Originator();
        originator.setState("state #1");
        originator.setState("state #2");
        careTaker.add(originator.saveToMemento());
        originator.setState("state #3");
        careTaker.add(originator.saveToMemento());
        originator.setState("state #4");
        System.out.println("current State:"+originator.getState());
        System.out.println("first saved state:"+careTaker.get(0).getState());
        System.out.println("second saved state:"+careTaker.get(1).getState());
    }
}
