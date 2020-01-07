package com.primary.example.util.test;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.Comparator;

/**
 * com.primary.example.util.test.ObjectUtilsTest
 *
 * @author lipeng
 * @date 2020/1/7 下午3:08
 */
public class ObjectUtilsTest {

    @Test
    public void test() {
        // 结果为true
        System.out.println(ObjectUtils.allNotNull(new Object[]{"str", "null"}));
        // 结果为false
        System.out.println(ObjectUtils.allNotNull(new Object[]{"str", "null", null}));
        // 结果为true
        System.out.println(ObjectUtils.anyNotNull(new Object[]{"str", "null", null}));

        // 结果为1
        System.out.println(ObjectUtils.compare(null, 1));
        // 结果为-1
        System.out.println(ObjectUtils.compare(1, null));
        // 结果为0
        System.out.println(ObjectUtils.compare(1000,1000));

        // 结果为false
        System.out.println(ObjectUtils.notEqual(null, null));
        // 结果为true
        System.out.println(ObjectUtils.notEqual(null, "str"));
        // 结果为trus
        System.out.println(ObjectUtils.notEqual("str", null));
        // 结果为false
        System.out.println(ObjectUtils.notEqual("str", "str"));

        // 结果为default value
        System.out.println(ObjectUtils.defaultIfNull(null, "default value"));

        // 返回第一个不为null的，结果为str
        System.out.println(ObjectUtils.firstNonNull(new Object[]{null, null, "str"}));

        // 返回最大值，结果为666
        System.out.println(ObjectUtils.max(new Integer[]{100, 2, 80, 99, 666}));
        // 返回最小值，结果为2
        System.out.println(ObjectUtils.min(new Integer[]{100, 2, 80, 99, 666}));

        // 按照升序排列，找到(size - 1) / 2位置的值
        System.out.println(ObjectUtils.median(new Integer[]{100, 2, 80, 99, 666}));

        // 按照降序排列，找到(size - 1) / 2位置的值
        System.out.println(ObjectUtils.median(Comparator.reverseOrder(), new Integer[]{100, 2, 80, 25, 666}));
    }
}
