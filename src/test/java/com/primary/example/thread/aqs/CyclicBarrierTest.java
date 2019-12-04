package com.primary.example.thread.aqs;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * com.primary.example.thread.aqs.CycliBarrierTest
 *
 * 1、指定需要调用await()方法的数量
 * 2、调用await()方法阻塞在栅栏处
 * 3、如果所有的线程都达到栅栏处，首先执行barrierAction，然后唤醒所有阻塞线程
 * 4、CyclicBarrier可以重复使用
 *
 * @author lipeng
 * @date 2019/11/20 下午3:49
 */
public class CyclicBarrierTest {

    private static final Integer THREAD_NUMBER = 5;

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUMBER, () -> {
        System.out.println("子任务执行完成");
    });

    @Test
    public void test() {
        for (int i = 0; i < THREAD_NUMBER * 2; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    System.out.println("线程：" + Thread.currentThread().getName() + "，开始执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        while (true) {

        }
    }
}
