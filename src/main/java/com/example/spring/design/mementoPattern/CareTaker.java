package com.example.spring.design.mementoPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Staro
 * @date: 2019/12/26 9:10
 * @Description:
 */
public class CareTaker {
    private List<Memento> mementoList=new ArrayList<>();

    public void add(Memento memento){
        mementoList.add(memento);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
