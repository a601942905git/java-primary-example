package com.primary.example.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * com.primary.example.thread.lock.MyLock
 *
 * wait()方法必须在同步代码块中，该方法会使线程等待并释放锁
 * notify()方法用来唤醒对象监视器上阻塞的线程
 *
 * @author lipeng
 * @date 2019/11/14 下午1:44
 */
public class MyLock implements Lock {

    private volatile boolean lock = false;

    @Override
    public synchronized void lock() {
        // 如果锁已经被其它线程获取，当前线程进行等待
        while (lock) {
            try {
                // 当前线程处于等待状态并释放锁，直到其它线程来唤醒，唤醒后，重新获取锁，然后进行执行
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock = true;
    }

    @Override
    public synchronized void unlock() {
        // 修改锁状态
        lock = false;
        // 唤醒当前对象监视器上处于等待状态的线程
        this.notify();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
