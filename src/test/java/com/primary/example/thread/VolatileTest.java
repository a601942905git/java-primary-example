package com.primary.example.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * com.primary.example.thread.VolatileTest
 *
 * volatile关键字作用：
 * 1、可以保证可见性，某一线程对共享资源的修改，对其它线程是可见的
 * 2、顺序性，防止CPU优化进行指令重排
 * 3、不能保证原子性
 *
 * @author lipeng
 * @date 2019/11/12 下午2:51
 */
public class VolatileTest {

    /**
     * 通过添加、删除volatile关键字来查看效果
     */
    private volatile boolean flag = false;

    @Test
    public void test() throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "，第" + i + "次执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            volatileTest.flag = true;
        }).start();

        new Thread(() -> {
            while (!volatileTest.flag) {
            }
            System.out.println("当前标识：" + volatileTest.flag);
        }).start();

        Thread.sleep(12000);
    }
}
