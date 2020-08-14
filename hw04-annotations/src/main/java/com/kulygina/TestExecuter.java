package com.kulygina;

import com.kulygina.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestExecuter {
    private static List<Method> testMethods = new ArrayList<>();
    private static List<Method> beforeMethods = new ArrayList<>();
    private static List<Method> afterMethods = new ArrayList<>();
    private static List<Method> beforeAllMethods = new ArrayList<>();
    private static List<Method> afterAllMethods = new ArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String className = args[0];
        Class<?> myClass = Class.forName(className);
        Method[] methods = myClass.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getDeclaredAnnotations().length == 0) continue;
            if(method.getDeclaredAnnotations()[0].annotationType().equals(BeforeAll.class)) {
                beforeAllMethods.add(method);
            }
            if(method.getDeclaredAnnotations()[0].annotationType().equals(Before.class)) {
                beforeMethods.add(method);
            }
            if(method.getDeclaredAnnotations()[0].annotationType().equals(Test.class)) {
                testMethods.add(method);
            }
            if(method.getDeclaredAnnotations()[0].annotationType().equals(After.class)) {
                afterMethods.add(method);
            }
            if(method.getDeclaredAnnotations()[0].annotationType().equals(AfterAll.class)) {
                afterAllMethods.add(method);
            }
        }

        for (Method beforeAllMethod : beforeAllMethods) {
            beforeAllMethod.invoke(myClass);
        }
        for (Method testMethod : testMethods) {
            Object instance = myClass.getConstructor().newInstance();
            for (Method beforeMethod : beforeMethods) {
                beforeMethod.invoke(instance);
            }
            testMethod.invoke(instance);
            for (Method afterMethod : afterMethods) {
                afterMethod.invoke(instance);
            }
        }
        for (Method afterAllMethod : afterAllMethods) {
            afterAllMethod.invoke(myClass);
        }
    }
}
