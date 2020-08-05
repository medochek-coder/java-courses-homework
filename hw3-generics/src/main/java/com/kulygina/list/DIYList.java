package com.kulygina.list;

import java.util.*;


public class DIYList<E> extends AbstractList<E> implements List<E>, RandomAccess {

    private Object[] elementData;
    private int size;
    private final int constStartSize = 10;

    public DIYList() {
        size = 0;
        elementData = new Object[constStartSize];
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        expandIfNeed(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return super.addAll(index, c);
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) this.elementData[index];
    }

    @Override
    public E set(int index, E element) {
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    private void expandIfNeed(int neededCount) {
        if (elementData.length < neededCount) {
            Object[] newArray = new Object[(int) (elementData.length * 1.5)];
            System.arraycopy(elementData, 0, newArray, 0,
                    elementData.length);
            elementData = newArray;
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }

}
