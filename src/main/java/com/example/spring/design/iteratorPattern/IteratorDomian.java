package com.example.spring.design.iteratorPattern;


import com.example.spring.design.iteratorPattern.customList.CustomCollection;
import com.example.spring.design.iteratorPattern.iterator.Iterator;

/**
 * @author: Staro
 * @date: 2019/12/13 16:23
 * @Description:
 */
public class IteratorDomian {
    public static void main(String[] args) {
        CustomCollection<String> myList=new CustomCollection<>();
        myList.add("aaa");
        myList.add("bbb");
        Iterator<String> iterator=myList.getIterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
