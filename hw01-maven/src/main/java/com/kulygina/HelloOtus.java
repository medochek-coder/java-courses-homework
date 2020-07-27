package com.kulygina;

import com.google.common.collect.Lists;

import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        list.add("1234");
        System.out.println(list.get(0));
    }
}
