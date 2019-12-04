package com.primary.example.andalgorithm;

import org.junit.Test;

/**
 * com.primary.example.andalgorithm.BitAlgorithm
 * 位运算
 * @author lipeng
 * @date 2019-02-01 17:25
 */
public class BitAlgorithm {

    @Test
    public void test() {
        int num1 = 8;
        int num2 = 9;
        System.out.println("8的二进制为：" + Integer.toBinaryString(num1));
        System.out.println("9的二进制为：" + Integer.toBinaryString(num2));
        // 与运算二进制结果：1000
        System.out.println(num1 & num2);
        // 或运算二进制结果：1001
        System.out.println(num1 | num2);
        // 非运算二进制结果：0111
        System.out.println(~num1);
        // 异或二进制结果：0001
        System.out.println(num1 ^ num2);
    }
}
