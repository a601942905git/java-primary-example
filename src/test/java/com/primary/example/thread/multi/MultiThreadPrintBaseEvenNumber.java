package com.primary.example.thread.multi;

/**
 * com.primary.example.thread.MultiThreadPrintBaseEvenNumber
 *
 * @author lipeng
 * @date 2019-03-14 15:43
 */
public class MultiThreadPrintBaseEvenNumber {

    private static int number = 0;

    public static void main(String[] args) {
        new Thread(new PrintBaseEvenNumberThread()).start();
        new Thread(new PrintBaseEvenNumberThread()).start();
    }

    public static class PrintBaseEvenNumberThread implements Runnable {
        @Override
        public void run() {
            synchronized (MultiThreadPrintBaseEvenNumber.class) {
                while (true) {
                    // 唤起其它线程竞争锁
                    MultiThreadPrintBaseEvenNumber.class.notifyAll();
                    if (number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "，打印偶数：" + number);
                    } else {
                        System.out.println(Thread.currentThread().getName() + "，打印奇数：" + number);
                    }
                    number++;
                    if (number >= 100) {
                        break;
                    }
                    try {
                        // 释放锁，进入休眠状态
                        MultiThreadPrintBaseEvenNumber.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
