package com.kulygina;

import com.kulygina.list.DIYList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> myList = new DIYList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(8);
        myList.add(9);
        myList.add(10);
        myList.add(11);
        myList.add(12);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(8);
        myList.add(9);
        myList.add(10);
        myList.add(11);
        myList.add(12);
        System.out.println("My list is created: \n" + myList);
        System.out.println("Size: " + myList.size());
        myList.set(2,33);
        System.out.println("My list after myList.set(2,33): \n" + myList);
        System.out.println("Size: " + myList.size());
        myList.add(3, 34);
        System.out.println("My list after myList.add(3, 34): \n" + myList);
        System.out.println("Size: " + myList.size());
        List<Integer> forCopy = new DIYList<>();
        forCopy.add(22);
        forCopy.add(23);
        forCopy.add(24);
        Collections.addAll(myList, 21,22,23);
        System.out.println("My list after Collections.addAll(myList, 21,22,23): \n" + myList);
        System.out.println("Size: " + myList.size());
        Collections.copy(myList, forCopy);
        System.out.println("List for copy: \n" + forCopy);
        System.out.println("Size: " + forCopy.size());
        System.out.println("My list after Collections.copy(myList, forCopy): \n" + myList);
        System.out.println("Size: " + myList.size());
        Collections.sort(myList, Comparator.naturalOrder());
        System.out.println("My list after Collections.sort(myList, Comparator.naturalOrder()): \n" + myList);
    }
}
