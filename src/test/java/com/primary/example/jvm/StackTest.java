package com.primary.example.jvm;

/**
 * com.primary.example.jvm.StackTest
 *
 * @author lipeng
 * @date 2021/7/1 9:55 AM
 */
public class StackTest {

    public static void main(String[] args) {
        StackTest test = new StackTest();
        test.method1();
    }

    public int method1() {
        int i = 10;
        int j = 20;
        return i + j;
    }

    public double method2() {
        double k = 5.0;
        double m = 6.0;
        return k + m;
    }
}
