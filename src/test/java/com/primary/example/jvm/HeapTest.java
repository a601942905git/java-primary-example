package com.primary.example.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * com.primary.example.jvm.HeapDemo
 *
 * @author lipeng
 * @date 2021/7/5 1:35 PM
 */
public class HeapTest {

    /**
     * 添加vm options:-Xms600M -Xmx600M -XX:+PrintGCDetails，启动内容：
     *
     * Heap
     *  PSYoungGen      total 179200K, used 12288K [0x00000007b3800000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 153600K, 8% used [0x00000007b3800000,0x00000007b44001b8,0x00000007bce00000)
     *   from space 25600K, 0% used [0x00000007be700000,0x00000007be700000,0x00000007c0000000)
     *   to   space 25600K, 0% used [0x00000007bce00000,0x00000007bce00000,0x00000007be700000)
     *  ParOldGen       total 409600K, used 0K [0x000000079a800000, 0x00000007b3800000, 0x00000007b3800000)
     *   object space 409600K, 0% used [0x000000079a800000,0x000000079a800000,0x00000007b3800000)
     *  Metaspace       used 3180K, capacity 4556K, committed 4864K, reserved 1056768K
     *   class space    used 339K, capacity 392K, committed 512K, reserved 1048576K
     *
     *  PSYoungGen(179200K) = eden(153600K) + from(25600K) 或者 eden(153600K) + to(25600K)
     *  total(575M) = (PSYoungGen(179200K) + ParOldGen(409600K)) / 1024 = 575M;
     *
     *  jvm参数：-Xms600M -Xmx600M
     *  堆初始化和最大大小都为600M，输出结果却只有575M，是因为年轻代中只有eden + from或者eden + to可以同时存储对象
     *  计算的时候时候少计算一个from或者to，25600K / 1024 = 25M
     *
     */
    @Test
    public void test() {
        long totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("totalMemory：" + totalMemory + "M");
        System.out.println("maxMemory：" + maxMemory + "M");
    }

    /**
     * 模拟内存溢出场景，可以通过visualvm查看内存变化
     *
     * @throws InterruptedException 中断异常
     */
    @Test
    public void test1() throws InterruptedException {
        List<Picture> pictureList = new ArrayList<>();
        while (true) {
            Picture picture = new Picture(10 * 1024 * 1024);
            pictureList.add(picture);
            TimeUnit.MILLISECONDS.sleep(400);
        }
    }

    public static class Picture {

        private byte[] buffer;

        public Picture(int length) {
            buffer = new byte[length];
        }
    }

}
