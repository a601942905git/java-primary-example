package com.primary.example.bitmap;

import org.apache.lucene.util.RamUsageEstimator;
import org.junit.Test;

import java.time.Clock;
import java.util.BitSet;
import java.util.Scanner;

/**
 * com.primary.example.bitmap.SimpleBitMap
 *
 * @author lipeng
 * @date 2019-02-01 15:24
 */
public class SimpleBitMap {

    public static final int MAX_LOOP_COUNT = 1000000;


    @Test
    public void test1() {
        BitSet bitSet = new BitSet();
        for (int i = 0, len = MAX_LOOP_COUNT; i < len; i++) {
            bitSet.set(i, true);
        }
        System.out.println("占用内存大小：" + RamUsageEstimator.shallowSizeOf(bitSet));
        int num = getInputNum();
        Long time1 = Clock.systemDefaultZone().millis();
        System.out.println("输入的数字" + num + "在集合中" + bitSet.get(num));
        Long time2 = Clock.systemDefaultZone().millis();
        System.out.println("使用bitMap耗时：" + (time2 - time1));
    }

    @Test
    public void test2() {
        int[] intArray = new int[MAX_LOOP_COUNT];
        for (int i = 0, len = MAX_LOOP_COUNT; i < len; i++) {
            intArray[i] = i;
        }
        System.out.println("占用内存大小：" + RamUsageEstimator.shallowSizeOf(intArray));
        int num = getInputNum();
        Long time1 = Clock.systemDefaultZone().millis();
        for (int i = 0, len = intArray.length; i < len; i++) {
            if (intArray[i] == num) {
                System.out.println("输入的数字" + num + "在集合中");
                break;
            }
        }
        Long time2 = Clock.systemDefaultZone().millis();
        System.out.println("使用数组耗时：" + (time2 - time1));
    }

    private int getInputNum() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int num = Integer.valueOf(str);
        return num;
    }
}
