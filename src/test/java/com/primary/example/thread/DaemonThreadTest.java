package com.primary.example.thread;

import org.junit.Test;

/**
 * com.primary.example.thread.DaemonThreadTest
 *
 * 系统中的线程分为：
 * 用户线程：负责执行系统中的任务
 * 守护线程：负责守护系统，当系统中只剩下守护线程，那么jvm就会退出，守护线程也会随之退出，守护线程在退出的时候不会执行finally代码块
 *
 * @author lipeng
 * @date 2019/11/21 上午11:30
 */
public class DaemonThreadTest {

    @Test
    public void test() throws InterruptedException {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("i a alive");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("execute finally code block");
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread.sleep(800);
    }
}
