package com.primary.example.bitmap;

import org.junit.Test;

import java.util.Scanner;

/**
 * com.primary.example.bitmap.BitMapDemo
 *
 * @author lipeng
 * @date 2019-02-02 10:19
 */
public class BitMapDemo {

    @Test
    public void test() {
        BitMap bitMap = new BitMap(100);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String numStr = scanner.next();
            int num = Integer.valueOf(numStr);
            bitMap.add(num);
            System.out.println("number:" + num + "，是否存在：" + bitMap.contain(num));
            System.out.println("删除number：" + num);
            bitMap.clear(num);
            System.out.println("number:" + num + "，是否存在：" + bitMap.contain(num));
        }
    }
}
