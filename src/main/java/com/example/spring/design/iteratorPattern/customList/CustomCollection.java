package com.example.spring.design.iteratorPattern.customList;


import com.example.spring.design.iteratorPattern.collection.Collection;
import com.example.spring.design.iteratorPattern.iterator.Iterator;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * @author: Staro
 * @date: 2019/12/13 11:33
 * @Description:
 */
public class CustomCollection<E> implements Collection<E> {
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    transient Object[] elementData;
    private static final int DEFAULT_CAPACITY = 10;
    protected transient int modCount = 0;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private int size;

    public CustomCollection() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public Iterator<E> getIterator() {
        return new NameIterator<E>();
    }

    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }


    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    private class NameIterator<E> implements Iterator<E> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = CustomCollection.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
