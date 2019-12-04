package com.primary.example.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * com.primary.example.thread.CallableTest
 *
 * @author lipeng
 * @date 2019/11/19 下午4:12
 */
public class CallableTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        Callable callable = () -> "callable";

        FutureTask futureTask = new FutureTask(callable);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(futureTask);
        System.out.println(futureTask.get());
    }
}
