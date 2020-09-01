package com.kulygina.logging;

import com.kulygina.annotation.Log;

public class ClassForLogging implements IClassForLogging {
    @Log
    @Override
    public void saySmth(String text) {
        System.out.println(text);
    }

    @Override
    public void scream(String text) {
        System.out.println(text.toUpperCase() + "!");
    }

    @Log
    @Override
    public void singSong(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    @Log
    @Override
    public void silence() {
        System.out.println("Shhhhh..");
    }
}