package com.primary.example.jvm.gc;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * com.primary.example.jvm.gc.GarbageCollectorTest
 *
 * 1.-XX:+PrintCommandLineFlags：查看当前版本jdk使用的垃圾回收器，jdk8使用的是-XX:+UseParallelGC
 * 2.-XX:+UseParallelGC(新生代)和-XX:+UseParallelOldGC(老年代)配合使用
 *  2.1 运行程序，通过jps查看应用进程id
 *  2.2 通过jinfo -flag UseParallelOldGC 进程id查看是否使用ParallelOldGC
 *
 * @author lipeng
 * @date 2021/8/19 9:58 AM
 */
public class GarbageCollectorTest {

    @Test
    public void test() throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
