package com.primary.example.collection.list;

import org.junit.Test;

import java.util.*;

/**
 * com.primary.example.collection.list.ArrayListTest
 * ArrayList学习
 * @author lipeng
 * @date 2019-04-16 09:59
 */
public class ArrayListTest {

    /**
     * 集合的特点就是有序，允许随机访问
     * 内部的实现是通过数组来完成的
     * 扩容机制，当超出指定容量之后，会扩容为原来的1.5倍
     *
     */
    @Test
    public void test() {
        List<Integer> numbers = new ArrayList(5);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.stream().forEach(System.out::println);
    }

    /**
     * fail-fast：当调用集合的iterator方法后，只能调用iterator的add()、remove()方法来修改集合的结构
     * 否则会抛出ConcurrentModificationException异常
     */
    @Test
    public void test1() {
        List<Integer> numbers = new ArrayList(5);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.removeIf(integer -> Objects.equals(integer, 1));
        System.out.println(numbers);

        numbers.removeIf(integer -> Objects.equals(integer, 2));
        System.out.println(numbers);
    }

    @Test
    public void test2() {
        List<Integer> numbers = new ArrayList(5);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        Iterator<Integer> iterator = numbers.iterator();
        numbers.remove(2);
        // 报错java.util.ConcurrentModificationException
        iterator.next();
        System.out.println(numbers);
    }

    @Test
    public void test3() {
        List<Integer> numbers = new ArrayList(5);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            numbers.remove(1);
            iterator.remove();
        }
        System.out.println(numbers);
    }
}
