package com.primary.example.thread.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * com.primary.example.thread.LockTest
 *
 * @author lipeng
 * @date 2019/11/12 下午8:25
 */
public class LockTest {

    private ReentrantLock reentrantLock = new ReentrantLock();

    @Test
    public void test() throws InterruptedException {
        // 中断当前线程
        Thread.currentThread().interrupt();
        // 获取当前线程的中断状态，不会清除中断标识
        System.out.println(Thread.currentThread().isInterrupted());
        try {
            // 中断获取锁，如果当前线程中断会清除中断标识并抛出中断异常
            reentrantLock.lockInterruptibly();
        } catch (Exception e) {
            // 中断标识已被清除
            System.out.println(Thread.currentThread().isInterrupted());
        }
        System.out.println("加锁中。。。。。。");
    }
}
