package com.primary.example.thread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * com.primary.example.thread.AtomicReferenceTest
 *
 *  AtomicReference<Integer>和AtomicInteger的区别：
 *  1、比较的方式不同，AtomicReference比较的是value的地址是否一样，AtomicInteger比较的是value的值是否一样
 *  2、AtomicInteger提供了更多操作value的方法，比如getAndIncrement、getAndDecrement、incrementAndGet、decrementAndGet
 *
 * @author lipeng
 * @date 2019/11/12 下午4:24
 */
public class AtomicReferenceTest {

    private AtomicReference<Integer> atomicReference1 = new AtomicReference<>(127);
    private AtomicReference<Integer> atomicReference2 = new AtomicReference<>(129);

    private AtomicInteger atomicInteger1 = new AtomicInteger(127);
    private AtomicInteger atomicInteger2 = new AtomicInteger(129);

    @Test
    public void testAtomicReference() {
        atomicReference1.compareAndSet(127, 130);
        atomicReference2.compareAndSet(129, 130);
        System.out.println("atomicReference1 value：" + atomicReference1.get());
        System.out.println("atomicReference2 value：" + atomicReference2.get());
    }

    @Test
    public void testAtomicInteger() {
        atomicInteger1.compareAndSet(127, 130);
        atomicInteger2.compareAndSet(129, 130);
        System.out.println("atomicReference1 value：" + atomicInteger1.get());
        System.out.println("atomicReference2 value：" + atomicInteger2.get());
    }
}
