package com.primary.example.jvm.string;

import org.junit.Test;

/**
 * com.primary.example.jvm.string.StringPerfomance
 *
 * 1.for循环中进行字符串拼接，需要使用StringBuilder进行拼接
 * 2.正常字符串拼接会不断创建StringBuilder对象和String对象，可能会造成GC
 *
 * @author lipeng
 * @date 2021/7/17 10:20 AM
 */
public class StringPerformanceTest {

    @Test
    public void test1() {
        Long start = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 100000; i++) {
            str = str + i;
        }
        Long end = System.currentTimeMillis();
        // 总耗时：28272
        System.out.println("总耗时：" + (end - start));
    }

    @Test
    public void test2() {
        Long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append(i);
        }
        Long end = System.currentTimeMillis();
        // 总耗时：74
        System.out.println("总耗时：" + (end - start));
    }
}
