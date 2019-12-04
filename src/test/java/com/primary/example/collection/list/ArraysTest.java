package com.primary.example.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * com.primary.example.collection.list.ArraysTest
 *
 * @author lipeng
 * @date 2019-08-16 11:06
 */
public class ArraysTest {

    @Test
    public void test() {
        List<Integer> numbers = Arrays.asList(1, 6, 8, 2, 7, 9);
        // 报java.lang.UnsupportedOperationException异常
        // 抛异常的原因是Arrays.asList返回的ArrayList是Arrays内部定义的ArrayList，并非java.util.ArrayList
        // Arrays内部的ArrayList的add方法直接调用父类AbstractList.add(int, E)，该方法直接抛出UnsupportedOperationException异常
        numbers.add(3);
    }

    @Test
    public void test1() {
        // 解决方案一
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 6, 8, 2, 7, 9));
        numbers.add(666);
        numbers.stream().forEach(System.out::println);
    }

    @Test
    public void test2() {
        // 解决方案二
        Integer[] numbers = new Integer[]{1, 6, 8, 2, 7, 9};
        List<Integer> list = new ArrayList<>(numbers.length);
        Collections.addAll(list, numbers);
        list.add(888);
        list.stream().forEach(System.out::println);
    }
}
