package com.primary.example.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.primary.example.thread.ThreadLocalTest
 *
 * @author lipeng
 * @date 2019/11/28 上午10:41
 */
public class ThreadLocalTest {
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /**
     * 以visualVM运行该程序
     * 在monitor选项中点击Heap Dump导出堆信息
     * 查看java.lang.Thread信息，会发现Thread中ThreadLocalMap中Entry中存在key为null的value
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                threadLocal.set(ATOMIC_INTEGER.incrementAndGet());
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(3);
        threadLocal = null;
        while (true) {

        }
    }

    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        InheritableThreadLocal<String> parent = new InheritableThreadLocal();
        parent.set("value-set-in-parent");

        // 初始化的时候会父线程的inheritableThreadLocals传给子线程
        Thread thread = new Thread(() ->
                System.out.println("子线程：" + Thread.currentThread().getName() + "，获取父线程中设置的value：" + parent.get()));
        thread.start();
        while (true) {

        }
    }

    @Test
    public void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        InheritableThreadLocal<String> parent = new InheritableThreadLocal();
        parent.set("value-set-in-parent");

        /**
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent
         */
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(parent));
        }
        while (true) {

        }
    }

    @Test
    public void test3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        InheritableThreadLocal<String> parent = new InheritableThreadLocal();

        /**
         * 模拟10次调用，打印结果如下：由于线程池中的线程复用问题，导致获取的value不是最新的value
         *
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent3
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent2
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent1
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent1
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent3
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent2
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent3
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent2
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent1
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent3
         */
        for (int i = 0; i < 10; i++) {
            parent.set("value-set-in-parent" + (i + 1));

            executorService.execute(new Task(parent));
        }
        while (true) {

        }
    }

    /**
     * 参考地址 @link {https://github.com/alibaba/transmittable-thread-local}
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal();

        /**
         * 修饰Runnable和Callable
         * 模拟10次调用，打印结果如下：可以看到每次执行任务获取到的都是最新的value
         *
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent2
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent1
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent3
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent6
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent4
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent5
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent7
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent8
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent9
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent10
         */
        for (int i = 0; i < 10; i++) {
            parent.set("value-set-in-parent" + (i + 1));
            executorService.execute(TtlRunnable.get(new Task(parent)));
        }
        while (true) {

        }
    }

    /**
     * 参考地址 @link {https://github.com/alibaba/transmittable-thread-local}
     * @throws InterruptedException
     */
    @Test
    public void test5() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);
        TransmittableThreadLocal<String> parent = new TransmittableThreadLocal();

        /**
         * 修饰线程池
         * 模拟10次调用，打印结果如下：可以看到每次执行任务获取到的都是最新的value
         *
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent3
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent1
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent2
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent4
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent6
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent5
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent8
         * 子线程：pool-1-thread-2，获取父线程中设置的value：value-set-in-parent9
         * 子线程：pool-1-thread-1，获取父线程中设置的value：value-set-in-parent7
         * 子线程：pool-1-thread-3，获取父线程中设置的value：value-set-in-parent10
         */
        for (int i = 0; i < 10; i++) {
            parent.set("value-set-in-parent" + (i + 1));
            ttlExecutorService.execute(new Task(parent));
        }
        while (true) {

        }
    }

    public class Task implements Runnable {
        ThreadLocal<String> threadLocal;

        public Task(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程：" + Thread.currentThread().getName() + "，获取父线程中设置的value：" + threadLocal.get());
        }
    }

}
