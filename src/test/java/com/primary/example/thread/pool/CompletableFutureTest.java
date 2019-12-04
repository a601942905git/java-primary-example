package com.primary.example.thread.pool;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * com.primary.example.thread.pool.CompletableFutureTest
 *
 * @author lipeng
 * @date 2019/12/3 下午5:02
 */
public class CompletableFutureTest {

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Task(), executorService);
        completableFuture.whenComplete((result, ex) -> System.out.println("执行结果：" + result));
        TimeUnit.SECONDS.sleep(5);
    }

    public class Task implements Supplier<String> {

        @Override
        public String get() {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "任务执行完成";
        }
    }
}
