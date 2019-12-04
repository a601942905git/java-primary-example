package com.primary.example.thread.aqs;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.primary.example.thread.aqs.CountDownLatch
 *
 * @author lipeng
 * @date 2019/11/18 下午8:39
 */
public class CountDownLatchTest {

    public static final Integer THREAD_NUMBER = 3;

    private final CountDownLatch countDownLatch1 = new CountDownLatch(1);

    private final CountDownLatch countDownLatch2 = new CountDownLatch(THREAD_NUMBER);

    /**
     * 当满足某一条件，唤醒所有阻塞线程执行任务
     */
    @Test
    public void test() {
        int count = 3;
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    countDownLatch1.await();
                    System.out.println(Thread.currentThread().getName() + "，子任务开始执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        while (true) {
            if (--count == 0) {
                System.out.println("count：" + count + "，开始执行所有子任务");
                countDownLatch1.countDown();
                break;
            }
        }
    }

    /**
     * 用于将复杂的任务拆分成多个子任务，阻塞等待子任务执行完成并获取执行结果
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                atomicInteger.incrementAndGet();
                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch2.await();
        System.out.println("阻塞等待所有子任务执行完成并获取结果：" + atomicInteger.get());
    }

    /**
     * 重复使用CountDownLatch
     * 第一部分调用await()可以阻塞，直到计数减到0为止
     * 第二部分调用await()不能阻塞，证明CountDownLatch是单向性的，不能重复使用
     * 如果需要重复使用可以考虑CyclicBarrier
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicInteger.incrementAndGet();
                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch2.await();
        System.out.println("阻塞等待所有子任务执行完成并获取结果：" + atomicInteger.get());

        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicInteger.incrementAndGet();
                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch2.await();
        System.out.println("阻塞等待所有子任务执行完成并获取结果：" + atomicInteger.get());
    }
}
