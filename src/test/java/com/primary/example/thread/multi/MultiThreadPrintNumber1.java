package com.primary.example.thread.multi;

import java.util.concurrent.locks.ReentrantLock;

/**
 * com.primary.example.thread.MultiThreadPrintNumber1
 *
 * @author lipeng
 * @date 2019-03-14 15:56
 */
public class MultiThreadPrintNumber1 {

    private static int count = 0;

    public static final int MAX_COUNT = 100;

    public static final int THREAD_NUMBER = 10;

    public static int current = 0;

    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(new TestThread(LOCK, i)).start();
        }
    }

    private static class TestThread implements Runnable {

        private ReentrantLock lock;

        private int number;

        public TestThread(ReentrantLock lock, int number) {
            this.lock = lock;
            this.number = number;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    lock.lock();
                    if (current == number) {
                        if (count >= MAX_COUNT) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "：" + count);
                        count++;
                        current++;
                        if (current > THREAD_NUMBER - 1) {
                            current = 0;
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
