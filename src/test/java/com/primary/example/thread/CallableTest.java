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
        Callable<String> callable = () -> "callable";;

        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
