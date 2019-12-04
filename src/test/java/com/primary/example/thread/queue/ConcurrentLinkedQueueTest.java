package com.primary.example.thread.queue;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * com.primary.example.thread.queue.ConcurrentLinkedQueueTest
 *
 * 1、底层采用单向链表的数据结构
 * 2、通过CAS + 自旋来实现线程安全
 * 3、无界队列
 *
 * @author lipeng
 * @date 2019/11/29 下午3:13
 */
public class ConcurrentLinkedQueueTest {

    @Test
    public void test() {
        ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue.add(1);
        concurrentLinkedQueue.add(2);
    }
}
