package com.primary.example.util.test;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Calendar;

/**
 * com.primary.example.util.test.ApacheDateUtilsTest
 *
 * @author lipeng
 * @date 2021/12/4 11:27 AM
 */
public class ApacheDateUtilsTest {

    /**
     * 一天过去的小时数
     */
    @Test
    public void hoursOfDay() {
        System.out.println(DateUtils.getFragmentInHours(Calendar.getInstance(), Calendar.DAY_OF_MONTH));
    }

    /**
     * 一天过去的秒数
     */
    @Test
    public void secondsOfDay() {
        System.out.println(DateUtils.getFragmentInSeconds(Calendar.getInstance(), Calendar.DAY_OF_MONTH));
    }

    /**
     * 一天过去的毫秒数
     */
    @Test
    public void millsOfDay() {
        System.out.println(DateUtils.getFragmentInMilliseconds(Calendar.getInstance(), Calendar.DAY_OF_MONTH));
    }

    /**
     * 一个月过去的天数
     */
    @Test
    public void daysOfMonth() {
        System.out.println(DateUtils.getFragmentInDays(Calendar.getInstance(), Calendar.MONTH));
    }

    /**
     * 一年过去的天数
     */
    @Test
    public void daysOfYear() {
        System.out.println(DateUtils.getFragmentInDays(Calendar.getInstance(), Calendar.YEAR));
    }
}
