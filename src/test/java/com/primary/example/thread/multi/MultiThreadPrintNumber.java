package com.primary.example.thread.multi;

import java.util.concurrent.Semaphore;

/**
 * com.primary.example.thread.Test
 * 整体思路，每个线程持有一把锁，初始化，只有第一个线程有锁
 * 执行完成之后把锁传递下去
 * @author lipeng
 * @date 2019-03-14 09:49
 */
public class MultiThreadPrintNumber {

    /**
     * 最大线程数
     */
    public static final int THREAD_NUMBER = 10;

    /**
     * 最大数
     */
    public static final int MAX_COUNT = 100;

    /**
     * 输出值
     */
    private static int count = 0;

    public static void main(String[] args) {
        Semaphore[] semaphores = new Semaphore[THREAD_NUMBER];
        for (int i = 0; i < THREAD_NUMBER; i++) {
            if (i == 0) {
                semaphores[i] = new Semaphore(1);
            } else {
                semaphores[i] = new Semaphore(0);
            }
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(new TestThread(semaphores, i)).start();
        }
    }


    public static class TestThread implements Runnable {

        private Semaphore[] semaphores;

        private int number = 0;

        public TestThread(Semaphore[] semaphores, int number) {
            this.number = number;
            this.semaphores = semaphores;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    semaphores[number].acquire();
                    if (count >= MAX_COUNT) {
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "：" + count);
                    count++;
                    int current = number + 1;
                    if (current >= THREAD_NUMBER) {
                        current = 0;
                    }
                    semaphores[current].release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
