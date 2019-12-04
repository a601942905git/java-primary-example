package com.primary.example.thread.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * com.primary.example.thread.lock.ConditionTest
 *
 * @author lipeng
 * @date 2019/11/26 下午3:19
 */
public class ConditionTest {

    private static final Lock LOCK = new ReentrantLock();

    private static Integer count = 0;

    private static final Integer MAX_COUNT = 100;

    private static final Condition FULL_CONDITION = LOCK.newCondition();

    private static final Condition EMPTY_CONDITION = LOCK.newCondition();

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer()).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer()).start();
        }
        while (true) {

        }
    }

    public static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    LOCK.lock();
                    while (count + 1 > MAX_COUNT) {
                        FULL_CONDITION.await();
                    }
                    count++;
                    EMPTY_CONDITION.signalAll();
                    System.out.println("生产线程：" + Thread.currentThread().getName() + "，开始执行，count：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }


    public static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    LOCK.lock();
                    while (count - 1 <= 0) {
                        EMPTY_CONDITION.await();
                    }
                    count--;
                    FULL_CONDITION.signalAll();
                    System.out.println("消费线程：" + Thread.currentThread().getName() + "，开始执行，count：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }
}
