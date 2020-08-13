package com.primary.example.compare;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * com.primary.example.compare.NumberCompare
 *
 * @author lipeng
 * @date 2020/8/5 7:55 PM
 */
public class NumberCompare {

    @Test
    public void test() {
        BigDecimal bigDecimal = new BigDecimal("5.0");
        BigDecimal bigDecimal1 = new BigDecimal("5.00");
        System.out.println(bigDecimal.equals(bigDecimal1));
        System.out.println(bigDecimal.compareTo(bigDecimal1));
    }
}
