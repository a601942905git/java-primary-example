package com.primary.example.thread;

import org.junit.Test;

import java.util.concurrent.Exchanger;

/**
 * com.primary.example.thread.ExchangerTest
 *
 * 为两个线程提供一个同步点，当两个线程都到达同步点后进行数据交换
 * 如果一个线程先到达同步点则进行阻塞等待另一个线程也到达同步点
 *
 * @author lipeng
 * @date 2019/12/3 下午5:22
 */
public class ExchangerTest {

    @Test
    public void test() {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                System.out.println("交换后的数据1：" + exchanger.exchange(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("交换后的数据2：" +exchanger.exchange(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
