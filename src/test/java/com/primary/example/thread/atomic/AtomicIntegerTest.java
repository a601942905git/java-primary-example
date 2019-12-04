package com.primary.example.thread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.primary.example.thread.atomic.AtomicIntegerTest
 *
 * @author lipeng
 * @date 2019/11/26 上午9:53
 */
public class AtomicIntegerTest {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                ATOMIC_INTEGER.incrementAndGet();
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new AddThread());
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println("result：" + ATOMIC_INTEGER.get());
    }
}
