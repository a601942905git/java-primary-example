package com.primary.example.collection.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * com.primary.example.collection.stack.StackTest
 *
 * @author lipeng
 * @date 2019-04-16 11:15
 */
public class StackTest {

    /**
     * 栈的特点是先进后出
     *
     * peek()取栈顶元素，但是不删除元素
     * pop()删除并返回栈顶元素
     */
    @Test
    public void stackTest() {
        Stack<Integer> stack = new Stack();
        for (int i = 1; i <= 10; i++) {
            System.out.println("入栈元素顺序为：" + stack.push(i));
        }

        System.out.println("取栈顶元素：" + stack.peek());

        for (int i = 1; i <= 10; i++) {
            System.out.println("出栈元素顺序为：" + stack.pop());
        }

    }
}
