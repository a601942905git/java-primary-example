package com.primary.example.thread.lock;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * com.primary.example.thread.lock.ReentrantLockTest
 *
 * @author lipeng
 * @date 2019/11/18 上午10:55
 */
public class ReentrantLockTest {

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Integer THREAD_NUMBER = 3;

    @Test
    public void test() {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    LOCK.lock();
                    Thread.sleep(300000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }).start();
        }

        while (true) {

        }
    }
}
