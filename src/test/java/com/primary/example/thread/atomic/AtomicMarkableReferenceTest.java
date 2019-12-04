package com.primary.example.thread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * com.primary.example.thread.atomic.AtomicMarkableReferenceTest
 *
 * @author lipeng
 * @date 2019/11/26 下午1:55
 */
public class AtomicMarkableReferenceTest {

    private static final AtomicMarkableReference MARKABLE_REFERENCE =
            new AtomicMarkableReference(10, false);

    @Test
    public void test() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            boolean isMarked = MARKABLE_REFERENCE.isMarked();
            MARKABLE_REFERENCE.compareAndSet(10, 20, isMarked, !isMarked);
        });
        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(() -> {
            boolean isMarked = MARKABLE_REFERENCE.isMarked();
            MARKABLE_REFERENCE.compareAndSet(20, 10, isMarked, !isMarked);
        });
        thread2.start();
        thread2.join();

        Thread thread3 = new Thread(() -> {
            boolean isMarked = MARKABLE_REFERENCE.isMarked();
            MARKABLE_REFERENCE.compareAndSet(10, 30, isMarked, !isMarked);
        });
        thread3.start();
        thread3.join();
        System.out.println(MARKABLE_REFERENCE.getReference());
    }
}
