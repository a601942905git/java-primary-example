package com.primary.example.thread.aqs;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * com.primary.example.thread.aqs.SemaphoreTest
 *
 * 控制并发的线程数量
 * 可以用来实现系统限流功能
 *
 * @author lipeng
 * @date 2019/11/20 下午3:27
 */
public class SemaphoreTest {

    public static final Integer THREAD_NUMBER = 10;

    private Semaphore semaphore = new Semaphore(5);

    @Test
    public void test() {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程：" + Thread.currentThread().getName() + "，正在执行任务");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
        while (true) {

        }
    }
}
