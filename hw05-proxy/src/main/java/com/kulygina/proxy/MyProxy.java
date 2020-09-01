package com.kulygina.proxy;

import com.kulygina.logging.IClassForLogging;

public class MyProxy {
    public static void main(String[] args) {
        IClassForLogging myClass = IoC.createMyClassForLogging();
        myClass.saySmth("Hi!");
        myClass.silence();
        myClass.scream("aaaaa");
        myClass.singSong("abcd", "efg");
    }
}
