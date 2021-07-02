package com.primary.example.jvm;

/**
 * com.primary.example.jvm.StackTest
 *
 * 1.每个线程都有独立栈空间
 * 2.栈存储单位：栈帧
 * 3.方法的调用对应栈帧入栈
 * 4.方法的返回对应栈帧出栈
 * 5.栈帧组成：局部变量表、操作数栈、动态链接、方法返回地址
 * 6.局部变量表存储单位：slot
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
        // int k;
        // 局部变量在使用前必须要显式赋值
        // System.out.println(k);
        return i + j;
    }

    public double method2() {
        // 从局部变量表中可以看到0号slot存放的是当前对象的应用this
        // 从局部变量表中可以看到变量k占用2个slot
        double k = 5.0;
        double m = 6.0;
        return k + m;
    }
}
