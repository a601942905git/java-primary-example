package com.primary.example.thread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * com.primary.example.thread.atomic.AtomicLongTest
 *
 * @author lipeng
 * @date 2019/11/26 上午10:05
 */
public class AtomicLongTest {

    private static final AtomicLong ATOMIC_LONG = new AtomicLong();

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                ATOMIC_LONG.incrementAndGet();
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new AtomicLongTest.AddThread());
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println("result：" + ATOMIC_LONG.get());
    }
}
