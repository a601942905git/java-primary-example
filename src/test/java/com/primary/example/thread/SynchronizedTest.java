package com.primary.example.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * com.primary.example.thread.SynchronizedTest
 *
 * @author lipeng
 * @date 2019/11/12 下午2:02
 */
public class SynchronizedTest {

    public static final Integer THREAD_NUMBER = 10000;

    private static int value = 0;

    public int getNextValue() {
        try {
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value++;
    }

    /**
     * javap -verbose Synchronized.class
     * ACC_SYNCHRONIZED 同步标识
     *
     * @return int
     */
    public synchronized int getNextValueWithSynchronized() {
        return value++;
    }

    public static synchronized int getNextValueWithStaticSynchronized() {
        return value++;
    }

    /**
     * monitorenter 监视器进入
     * monitorexit  监视器退出
     *
     * @return int
     */
    public int getNextValueWithSynchronizedBlock() {
        synchronized (this) {
            return value++;
        }
    }

    @Test
    public void test() throws InterruptedException {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread[] threads = new Thread[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new Thread(synchronizedTest::getNextValue);
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }
        System.out.println("result：" + synchronizedTest.value);
    }

    @Test
    public void testWithSynchronized() throws InterruptedException {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread[] threads = new Thread[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new Thread(synchronizedTest::getNextValueWithSynchronized);
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }
        System.out.println("result：" + synchronizedTest.value);
    }

    @Test
    public void testWithStaticSynchronized() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new Thread(SynchronizedTest::getNextValueWithStaticSynchronized);
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }
        System.out.println("result：" + SynchronizedTest.value);
    }

    @Test
    public void testWithSynchronizedBlock() throws InterruptedException {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread[] threads = new Thread[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new Thread(synchronizedTest::getNextValueWithSynchronizedBlock);
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }
        System.out.println("result：" + synchronizedTest.value);
    }
}
