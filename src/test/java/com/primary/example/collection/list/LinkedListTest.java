package com.primary.example.collection.list;

import org.junit.Test;

import java.util.LinkedList;

/**
 * com.primary.example.collection.list.LinkedListTest
 * 链表
 * 链表顺序存储，不适合随机存取，适合结点添加和删除
 * 提供特殊的删除头尾节点方法，可以以此来实现栈和队列功能
 *
 * @author lipeng
 * @date 2019-04-16 10:56
 */
public class LinkedListTest {

    /**
     * 取元素
     */
    @Test
    public void getElement() {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        System.out.println("取第一个元素：" + numbers.getFirst());
        System.out.println("取第一个元素：" + numbers.getLast());
    }

    /**
     * 移除链表中的第一个元素
     */
    @Test
    public void removeFirst() {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("移除第一个元素：" + numbers.removeFirst());
        numbers.stream().forEach(System.out::println);
    }

    /**
     * 移除链表中的最后一个元素
     */
    @Test
    public void removeLast() {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("移除最后一个元素：" + numbers.removeLast());
        numbers.stream().forEach(System.out::println);
    }

    /**
     * 使用链表实现栈
     * 栈的特点是先进后出
     */
    @Test
    public void stackTest() {
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.println("元素入栈顺序为：" + i);
            numbers.add(i);
        }

        for (int i = 0, len = numbers.size(); i < len; i++) {
            System.out.println("出栈元素为：" + numbers.removeLast());
        }
    }

    /**
     * 使用链表实现队列
     * 队列的特点是先进先出
     */
    @Test
    public void queueTest() {
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.println("元素入队顺序为：" + i);
            numbers.add(i);
        }

        for (int i = 0, len = numbers.size(); i < len; i++) {
            System.out.println("出队元素为：" + numbers.removeFirst());
        }
    }
}
