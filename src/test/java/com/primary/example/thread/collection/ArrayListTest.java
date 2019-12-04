package com.primary.example.thread.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.primary.example.thread.collection.ArrayList
 *
 * @author lipeng
 * @date 2019/11/29 上午11:18
 */
public class ArrayListTest {

    /**
     * ArrayList线程不安全
     * 1、当使用多个线程同时调用ArrayList的add()方法，
     * 会抛出java.lang.ArrayIndexOutOfBoundsException
     * 2、原因在于当数组还剩一个元素可以添加的时候，此时多个线程都判断可以进行元素的添加，
     * 实际在添加的过程中数组的大小是不够的，故而抛出异常
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        List<Integer> numbers = new ArrayList<>();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    numbers.add(atomicInteger.incrementAndGet());
                }
            });
        }

        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }
    }

    /**
     * SynchronizedList线程安全
     * 1、依赖synchronized关键字来实现线程安全
     * 优势：实现线程安全
     * 缺点：同一时刻只能进行读或者写
     * 适用场景：读少写多
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    numbers.add(atomicInteger.incrementAndGet());
                }
            });
        }

        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }
    }

    /**
     * CopyOnWriteArrayList线程安全
     * 1、写的时候会使用ReentrantLock进行加锁，对原来的数组进行拷贝，修改副本，然后将数组设置为修改后的数组
     * 优势：实现线程安全，可以同时进行读写操作
     * 缺点：写操作需要进行拷贝数组，读操作会存在延迟
     * 适用场景：读多写少
     *
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        List<Integer> numbers = new CopyOnWriteArrayList<>();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    numbers.add(atomicInteger.incrementAndGet());
                }
            });
        }

        for (int i = 0; i < 100; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }
    }

}
