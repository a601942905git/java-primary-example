package com.primary.example.thread.pool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * com.primary.example.thread.pool.FutureTaskTest
 *
 * @author lipeng
 * @date 2019/12/3 下午2:57
 */
public class FutureTaskTest {

    /**
     * FutureTask原理
     * 启动线程执行FutureTask的run方法，然后在run方法里面调用Callable的call方法
     * get()方法进行阻塞，直到call()方法执行完成唤醒阻塞线程
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new Task());
        new Thread(futureTask).start();
        System.out.println("任务完成");
        System.out.println("人无返回结果：" + futureTask.get());
    }

    public class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(3);
            return "任务执行完成";
        }
    }

}
