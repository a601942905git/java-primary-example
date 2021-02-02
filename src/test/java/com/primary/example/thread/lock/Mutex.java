package com.primary.example.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * com.primary.example.thread.lock.Mutex
 *
 * 锁：
 * 1.限制对共享资源的访问
 * 2.可重入性
 * 3.互斥性
 *
 * 公平与非公平：
 * 公平：每次从队列取第一个节点执行锁竞争
 * 非公平：当前线程执行锁竞争
 *
 * 共享与独占：
 * 共享：同一时刻允许多个线程获取锁，如读与读之间
 * 独占：同一时刻只允许一个线程获取锁
 *
 * 自定义锁实现步骤：
 * 1、定义一个静态内部类Sync
 * 2、静态内部类Sync继承AbstractQueuedSynchronizer
 * 3、实现AbstractQueuedSynchronizer的tryAcquire、tryRelease
 * 4、通过tryAcquire、tryRelease来改变state的状态
 * 5、实现Lock接口，在实现方法中调用AbstractQueuedSynchronizer的模板方法
 *
 * @author lipeng
 * @date 2019/11/15 上午10:09
 */
@Slf4j
public class Mutex implements Lock, Serializable {

    public static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;
            Thread currentThread = Thread.currentThread();
            int state = getState();
            // 可以获取锁
            if (state == 0) {
                // 成功获取锁
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            } else {
                // 如果当前线程已经持有锁，则需要支持重入功能
                if (getExclusiveOwnerThread() == currentThread) {
                    setState(state + acquires);
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int release) {
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }

            boolean free = false;
            int c = getState() - release;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }

            setState(c);
            return free;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        Condition newCondition() {
            return new ConditionObject();
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            // reset to unlocked stat
            setState(0);
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public boolean isHeldExclusively() {
        return sync.isHeldExclusively();
    }
}
