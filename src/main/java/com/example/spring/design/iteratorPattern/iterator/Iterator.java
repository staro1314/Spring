package com.example.spring.design.iteratorPattern.iterator;

/**
 * @author: Staro
 * @date: 2019/12/13 11:27
 * @Description:
 */
public interface Iterator<E> {
    public boolean hasNext();
    public E next();
}
