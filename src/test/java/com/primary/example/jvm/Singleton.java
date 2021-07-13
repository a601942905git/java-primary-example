package com.primary.example.jvm;

/**
 * com.primary.example.jvm.Singleton
 *
 * 调用Singleton.getInstance()方法的时候会执行LazyHolder.INSTANCE
 * 此时会加载LazyHolder类并进行初始化，类的初始化过程会进行加锁，因此是线程安全的
 * @author lipeng
 * @date 2021/7/13 7:34 PM
 */
public class Singleton {

    private Singleton() {}

    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}