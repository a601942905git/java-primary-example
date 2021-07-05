package com.primary.example.jvm;

/**
 * com.primary.example.jvm.HeapDemo
 *
 * @author lipeng
 * @date 2021/7/5 1:35 PM
 */
public class HeapTest {

    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("totalMemory：" + totalMemory);
        System.out.println("maxMemory：" + maxMemory);
    }
}
