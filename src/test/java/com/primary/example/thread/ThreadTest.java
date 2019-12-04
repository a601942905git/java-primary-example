package com.primary.example.thread;

import org.junit.Test;

/**
 * com.primary.example.thread.ThreadTest
 *
 * @author lipeng
 * @date 2019/11/19 下午4:51
 */
public class ThreadTest {

    @Test
    public void joinTest() throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
