package com.example.spring.design.iteratorPattern.collection;


import com.example.spring.design.iteratorPattern.iterator.Iterator;

/**
 * @author: Staro
 * @date: 2019/12/13 11:29
 * @Description:
 */
public interface Collection<E> {
    public Iterator<E> getIterator();
    boolean add(E e);
}
