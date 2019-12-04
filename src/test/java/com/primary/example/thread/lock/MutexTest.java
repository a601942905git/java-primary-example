package com.primary.example.thread.lock;

import org.junit.Test;

/**
 * com.primary.example.thread.lock.MutexTest
 *
 * @author lipeng
 * @date 2019/11/15 上午10:27
 */
public class MutexTest {

    private final Mutex mutex = new Mutex();

    private static final Integer THREAD_NUMBER = 3;

    @Test
    public void testMyLock() throws InterruptedException {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    mutex.lock();
                    Thread.sleep(300000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                }
            }).start();
        }

        while (true) {

        }
    }
}
