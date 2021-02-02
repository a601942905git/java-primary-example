package com.primary.example.thread.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * com.primary.example.thread.lock.MutexTest
 *
 * @author lipeng
 * @date 2019/11/15 上午10:27
 */
@Slf4j
public class MutexTest {

    private final Mutex mutex = new Mutex();

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final Integer THREAD_NUMBER = 3;

    @Test
    public void testMyLock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            executorService.submit(new Task(countDownLatch));
        }

        countDownLatch.countDown();

        TimeUnit.SECONDS.sleep(10);
    }

    class Task implements Runnable {
        private final CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                log.error("thread interrupted exception：", e);
            }

            try {
                mutex.lock();
                log.info("current thread：{} acquire lock1，execute task", Thread.currentThread().getName());
                mutex.lock();
                log.info("current thread：{} acquire lock2，execute task", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                log.info("execute exception：", e);
            } finally {
                mutex.unlock();
                log.info("current thread：{} release lock1", Thread.currentThread().getName());
                mutex.unlock();
                log.info("current thread：{} release lock2", Thread.currentThread().getName());
            }
        }
    }

}
