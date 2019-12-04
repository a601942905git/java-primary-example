package com.primary.example.string;

import java.time.Clock;

/**
 * com.primary.example.string.StringAppendTest
 *
 * @author lipeng
 * @date 2019-01-21 09:43
 */
public class StringAppendDemo {

    public static final Integer LOOP_COUNT = 100000;

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        Long startTime = Clock.systemDefaultZone().millis();
        String str = "abc";
        for (int i = 0; i < LOOP_COUNT; i++) {
            str += "def";
        }
        Long endTime = Clock.systemDefaultZone().millis();
        System.out.println("+ cost time：" + (endTime - startTime));
    }

    public static void test2() {
        Long startTime = Clock.systemDefaultZone().millis();
        String str = "abc";
        for (int i = 0; i < LOOP_COUNT; i++) {
            str.concat("def");
        }
        Long endTime = Clock.systemDefaultZone().millis();
        System.out.println("concat cost time：" + (endTime - startTime));
    }

    public static void test3() {
        Long startTime = Clock.systemDefaultZone().millis();
        StringBuilder sb = new StringBuilder("abc");
        for (int i = 0; i < LOOP_COUNT; i++) {
            sb.append("def");
        }
        Long endTime = Clock.systemDefaultZone().millis();
        System.out.println("StringBuilder cost time：" + (endTime - startTime));
    }

    public static void test4() {
        Long startTime = Clock.systemDefaultZone().millis();
        StringBuffer sb = new StringBuffer("abc");
        for (int i = 0; i < LOOP_COUNT; i++) {
            sb.append("def");
        }
        Long endTime = Clock.systemDefaultZone().millis();
        System.out.println("StringBuffer cost time：" + (endTime - startTime));
    }
}
