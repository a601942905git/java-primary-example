package com.primary.example.util.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * com.primary.example.util.test.RandomUtils
 *
 * @author lipeng
 * @date 2020/1/7 下午3:05
 */
public class RandomUtilsTest {

    @Test
    public void test1() {
        // 生成10-20之间的long随机数，不包含20
        System.out.println(RandomUtils.nextLong(10, 20));
        // 生成0-10之间的int随机数，不包含10
        System.out.println(RandomUtils.nextInt(0, 10));
    }

    @Test
    public void test2() {
        // 中文环境下是乱码
        System.out.println(RandomStringUtils.random(5));

        // 使用指定的字符串生成5位随机数
        System.out.println(RandomStringUtils.random(5, "sfkslfdlk"));

        // 使用指定的字符生成10位随机数
        System.out.println(RandomStringUtils.random(10, new char[]{'a', 'm', 'd', 'h', 'l', '1', '4', '8', '2', '6'}));

        // 生成8位随机字符串
        System.out.println(RandomStringUtils.randomAlphabetic(8));
        // 生成6-8位随机字符串
        System.out.println(RandomStringUtils.randomAlphabetic(6, 9));

        // 生成8位由数字和字符组合的随机字符串
        System.out.println(RandomStringUtils.randomAlphanumeric(8));
        // 生成6-88位由数字和字符组合的随机字符串
        System.out.println(RandomStringUtils.randomAlphanumeric(6, 9));

        // 生成8位由数字组成的字符串
        System.out.println(RandomStringUtils.randomNumeric(8));
        // 生成6-8位由数字组成的字符串
        System.out.println(RandomStringUtils.randomNumeric(6, 9));
    }
}
