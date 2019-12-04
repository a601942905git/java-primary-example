package com.primary.example.thread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * com.primary.example.thread.atomic.AtomicIntegerArrayTest
 *
 * @author lipeng
 * @date 2019/11/26 上午10:48
 */
public class AtomicIntegerArrayTest {

    private static final AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                ATOMIC_INTEGER_ARRAY.incrementAndGet(i % ATOMIC_INTEGER_ARRAY.length());
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        Thread[] threads = new Thread[ATOMIC_INTEGER_ARRAY.length()];
        for (int i = 0; i < 10; i++) {
            Runnable target;
            threads[i] = new Thread(new AddThread());
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println(ATOMIC_INTEGER_ARRAY.toString());
    }
}
