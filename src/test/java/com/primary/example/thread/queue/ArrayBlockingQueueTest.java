package com.primary.example.thread.queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.primary.example.thread.queue.ArrayBlockingQueueTest
 *
 * 基于数组结构实现的有界阻塞队列
 * add方法添加成功返回true，队列满的情况下抛出异常、remove删除成功返回true，失败返回false
 * offer添加成功返回true，失败返回false、poll队列为空返回null
 * put阻塞添加元素、take阻塞删除元素
 * @author lipeng
 * @date 2019/11/28 上午10:07
 */
public class ArrayBlockingQueueTest {

    private static final ArrayBlockingQueue<Order> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(100);

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(10000);

    @Test
    public void test() {
        new Thread(new Consumer(ARRAY_BLOCKING_QUEUE)).start();
        new Thread(new Consumer(ARRAY_BLOCKING_QUEUE)).start();
        new Thread(new Consumer(ARRAY_BLOCKING_QUEUE)).start();
        new Thread(new Consumer(ARRAY_BLOCKING_QUEUE)).start();
        new Thread(new Consumer(ARRAY_BLOCKING_QUEUE)).start();

        Producer producer = new Producer(ARRAY_BLOCKING_QUEUE);
        while (true) {
            Order order = new Order(ATOMIC_INTEGER.incrementAndGet(), ATOMIC_INTEGER.get());
            producer.putOrder(order);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Producer {
        private ArrayBlockingQueue<Order> arrayBlockingQueue;

        public Producer(ArrayBlockingQueue<Order> arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        public void putOrder(Order order) {
            try {
                this.arrayBlockingQueue.put(order);
                System.out.println("订单" + order + "放入队列中");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public static class Consumer implements Runnable {

        private ArrayBlockingQueue<Order> arrayBlockingQueue;

        public Consumer(ArrayBlockingQueue<Order> arrayBlockingQueue) {
            this.arrayBlockingQueue = arrayBlockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Order order = arrayBlockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + "：正在从队列中取出订单" + order + "进行消费");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Order {
        private Integer id;

        private Integer price;
    }
}
