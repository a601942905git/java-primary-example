package com.primary.example.thread.atomic;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * com.primary.example.thread.AtomicStampedReferenceTest
 *
 * 用来解决ABA的问题
 * 只有引用和版本一致才执行更新操作
 * 特别注意：Integer的取值范围-127~128
 *
 * @author lipeng
 * @date 2019/11/12 下午4:10
 */
public class AtomicStampedReferenceTest {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(100);

    private static final AtomicStampedReference<Integer> STAMPED_REFERENCE =
            new AtomicStampedReference<>(10, 0);

    @Test
    public void test() {
        Runnable target;
        Thread thread1 = new Thread(() -> {
            ATOMIC_INTEGER.compareAndSet(100, 200);
            ATOMIC_INTEGER.compareAndSet(200, 100);
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            boolean updateFlag = ATOMIC_INTEGER.compareAndSet(100, 500);
            System.out.println("update flag：" + updateFlag +  "，update result：" + ATOMIC_INTEGER.get());
        });
        thread2.start();
    }

    @Test
    public void test1() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = STAMPED_REFERENCE.getStamp();
            STAMPED_REFERENCE.compareAndSet(10, 20, stamp, stamp + 1);
            stamp = STAMPED_REFERENCE.getStamp();
            STAMPED_REFERENCE.compareAndSet(20, 10, stamp, stamp + 1);
        });

        Thread thread2 = new Thread(() -> {
            int stamp = STAMPED_REFERENCE.getStamp();
            System.out.println("stamp：" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean updateFlag = STAMPED_REFERENCE.compareAndSet(10, 30, stamp, stamp + 1);
            System.out.println("update flag：" + updateFlag +  "，update result：" + STAMPED_REFERENCE.getReference());
        });
        thread1.start();
        thread2.start();
        thread2.join();
    }
}
