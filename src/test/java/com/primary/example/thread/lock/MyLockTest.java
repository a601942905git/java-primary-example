package com.primary.example.thread.lock;

import org.junit.Test;

/**
 * com.primary.example.thread.lock.MyLockTest
 *
 * @author lipeng
 * @date 2019/11/14 下午1:56
 */
public class MyLockTest {

    private int value;

    public static final Integer THREAD_NUMBER = 1000;

    @Test
    public void test() throws InterruptedException {
        MyLockTest myLockTest = new MyLockTest();
        Thread[] threads = new Thread[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myLockTest.value++;
            });
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }
        System.out.println("result：" + myLockTest.value);
    }

    @Test
    public void testMyLock() throws InterruptedException {
        MyLock myLock = new MyLock();
        MyLockTest myLockTest = new MyLockTest();
        Thread[] threads = new Thread[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myLock.lock();
                myLockTest.value++;
                myLock.unlock();
            });
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }
        System.out.println("result：" + myLockTest.value);
    }
}
