package com.kulygina;

import com.kulygina.annotation.*;

public class MyTest {
    
    private int value = 1;
    
    public MyTest() {
        System.out.println("value = " + value);
    }

    public void notTest() {
        System.out.println("You will newer see me");
    }

    @Test
    public void test1(){
        value = 0;
        System.out.println("test1");
        System.out.println("value = " + value);
    };

    @Test
    public void test2(){
        value = 0;
        System.out.println("test2");
        System.out.println("value = " + value);
    };

    @Test
    public void test3(){
        value = 0;
        System.out.println("test3");
        System.out.println("value = " + value);
    };

    @Before
    public void before1(){
        System.out.println("before1");
    };

    @Before
    public void before2(){
        System.out.println("before2");
    };

    @After
    public void after1(){
        System.out.println("after1");
    };

    @After
    public void after2(){
        System.out.println("after2");
    };

    @BeforeAll
    public static void beforeAll(){
        System.out.println("beforeAll");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("afterAll");
    };
}
