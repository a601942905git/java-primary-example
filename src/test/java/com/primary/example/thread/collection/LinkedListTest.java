package com.primary.example.thread.collection;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.primary.example.thread.collection.LinkedListTest
 *
 * @author lipeng
 * @date 2019/11/29 下午2:10
 */
public class LinkedListTest {

    /**
     * LinkedList线程不安全
     * 1、多个线程同时往链表中添加节点，会出现节点数小于链表的实际大小，在遍历链表的时候就会出现空指针异常
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        List<Integer> numbers = new LinkedList<>();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    numbers.add(atomicInteger.incrementAndGet());
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(numbers);
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
        List<Integer> numbers = Collections.synchronizedList(new LinkedList<>());
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    numbers.add(atomicInteger.incrementAndGet());
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println(numbers);
    }
}
