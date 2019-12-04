package com.primary.example.thread;

import org.junit.Test;

/**
 * com.primary.example.thread.ThreadInterruptTest
 *
 * isInterrupted实例方法：不会清除中断状态
 * interrupted静态方法：会清楚中断状态
 *
 * @author lipeng
 * @date 2019/11/18 上午10:44
 */
public class ThreadInterruptTest {

    @Test
    public void test1() {
        System.out.println("start");
        // 中断线程
        Thread.currentThread().interrupt();
        System.out.println("thread interrupt status：" + Thread.currentThread().isInterrupted());
        System.out.println("thread interrupt status：" + Thread.currentThread().isInterrupted());
        System.out.println("end");
    }

    @Test
    public void test2() {
        System.out.println("start");
        // 中断线程
        Thread.currentThread().interrupt();
        System.out.println("thread interrupt status：" + Thread.interrupted());
        System.out.println("thread interrupt status：" + Thread.interrupted());
        System.out.println("end");
    }
}
