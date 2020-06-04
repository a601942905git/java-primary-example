package com.primary.example.stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * com.primary.example.stream.ReduceTest
 *
 * @author lipeng
 * @date 2020/6/4 5:41 PM
 */
public class ReduceTest {

    @Test
    public void test() {
        // 找出最长的单词
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        String result = stream.reduce((str1, str2) -> str1.length() > str2.length() ? str1 : str2).orElse(StringUtils.EMPTY);
        System.out.println("最长的单词为：" + result);
    }

    @Test
    public void test1() throws InterruptedException {
        // 求单词长度之和
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        int worldTotal = stream.reduce(0, (sum, str) -> sum + str.length(), Integer::sum);
        System.out.println("单词长度之和：" + worldTotal);
    }

    @Test
    public void test2() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        /**
         * reduce的第三个参数只有在使用并行流的时候才会有用，在普通的stream()中不会有效
         */
        int worldTotal = numbers.stream().reduce(0, (sum, num) -> {
            System.out.println("current thread：" + Thread.currentThread().getName() + "，sum=" + sum + "，num=" + num);
            return sum + num;
        }, Integer::sum);
        System.out.println("单词长度之和：" + worldTotal);
    }

    @Test
    public void test3() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        /**
         * reduce的第三个参数只有在使用并行流的时候才会有用，在普通的stream()中不会有效
         */
        int worldTotal = numbers.parallelStream().reduce(0, (sum, num) -> {
            System.out.println("current thread：" + Thread.currentThread().getName() + "，sum=" + sum + "，num=" + num);
            return sum + num;
        }, Integer::sum);
        System.out.println("单词长度之和：" + worldTotal);
    }
}
