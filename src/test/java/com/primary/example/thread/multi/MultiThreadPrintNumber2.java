package com.primary.example.thread.multi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.primary.example.thread.MultiThreadPrintNumber2
 *
 * @author lipeng
 * @date 2019-03-14 16:23
 */
public class MultiThreadPrintNumber2 {

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static int count = 0;

    private static final int MAX_COUNT = 100;

    private static final int THREAD_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        Condition[] conditions = new Condition[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            conditions[i] = LOCK.newCondition();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(new TestThread(i, conditions)).start();
            if (i == 0) {
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

    public static class TestThread implements Runnable {

        private int number;

        private Condition[] conditions;

        public TestThread(int number, Condition[] conditions) {
            this.number = number;
            this.conditions = conditions;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    LOCK.lock();
                    int current = number + 1;
                    if (current > THREAD_NUMBER - 1) {
                        current = 0;
                    }
                    conditions[current].signal();
                    if (count > MAX_COUNT) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "：" + count);
                    count++;
                    conditions[number].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

}
