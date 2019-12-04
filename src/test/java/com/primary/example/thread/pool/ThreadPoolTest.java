package com.primary.example.thread.pool;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.primary.example.thread.pool.ThreadPoolTest
 *
 * @author lipeng
 * @date 2019/12/3 下午2:01
 */
public class ThreadPoolTest {

    /**
     * 线程池核心参数
     * corePoolSize：核心线程数
     * maximumPoolSize：最大线程数量
     * keepAliveTime：存活时间
     * unit：时间单位
     * workQueue：工作队列
     * threadFactory：线程工厂，用于创建线程
     * handler：拒绝策略
     *
     * 工作队列
     * SynchronousQueue：不存储任何元素
     * LinkedBlockingQueue：基于链表实现的阻塞队列
     * ArrayBlockingQueue：基于数组实现的阻塞队列
     *
     * 拒绝策略
     * AbortPolicy：直接抛异常
     * CallerRunsPolicy：调用线程执行任务
     * DiscardPolicy：抛弃任务
     * DiscardOldestPolicy：抛弃等待时间最长的任务
     */
    @Test
    public void test() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3, 3,
                120, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(500),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 6; i++) {
            threadPoolExecutor.execute(new Task());
        }


        while (true) {
            System.out.println("active thread count：" + threadPoolExecutor.getActiveCount());
            System.out.println("task count：" + threadPoolExecutor.getTaskCount());
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * 创建固定线程数的线程池
     * 队列为无界队列
     */
    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Task());
        while (true) {

        }
    }

    /**
     * 使用SynchronousQueue队列，无限创建线程
     */
    @Test
    public void test2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Task());
        while (true) {

        }
    }

    /**
     * 创建单一线程、无界队列的线程池
     */
    @Test
    public void test3() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Task());
        while (true) {

        }
    }

    /**
     * 10s后开始执行
     * 无线创建线程的无界队列
     */
    @Test
    public void test4() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.schedule(new Task(), 10, TimeUnit.SECONDS);
        while (true) {

        }
    }

    @Test
    public void test5() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.scheduleAtFixedRate(new Task(), 3, 1, TimeUnit.SECONDS);
        while (true) {

        }
    }

    @Test
    public void test6() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.scheduleWithFixedDelay(new Task(), 3, 1, TimeUnit.SECONDS);
        while (true) {

        }
    }


    @Test
    public void test7() {
        ExecutorService executorService = Executors.newWorkStealingPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
        while (true) {

        }
    }

    public class Task implements Runnable {
        @Override
        public void run() {
            AtomicInteger atomicInteger = new AtomicInteger();
            while (true) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "：" + atomicInteger.incrementAndGet());
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
