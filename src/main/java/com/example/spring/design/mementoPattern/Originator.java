package com.example.spring.design.mementoPattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Staro
 * @date: 2019/12/26 9:06
 * @Description:
 */
@Getter
@Setter
public class Originator {
    private String state;

    public Memento saveToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento){
        state=memento.getState();
    }
}
