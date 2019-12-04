package com.primary.example.thread.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * com.primary.example.thread.atomic.AtomicBooleanTest
 *
 * AtomicBoolean内部含有由volatile修饰的int变量
 * int值为1代表true，值为0代表false
 *
 * @author lipeng
 * @date 2019/11/26 上午10:07
 */
public class AtomicBooleanTest {

    private static final AtomicBoolean ATOMIC_BOOLEAN = new AtomicBoolean();

    @Test
    public void test() {
        System.out.println(ATOMIC_BOOLEAN.compareAndSet(true, false));
    }
}
