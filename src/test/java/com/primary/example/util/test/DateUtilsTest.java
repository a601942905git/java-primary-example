package com.primary.example.util.test;

import com.primary.example.util.DateUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * com.primary.example.util.test.DateUtilsTest
 *
 * @author lipeng
 * @date 2020/8/13 2:20 PM
 */
public class DateUtilsTest {

    @Test
    public void formatDateTime() {
        System.out.println(DateUtils.formatDateTime(new Date()));
    }

    @Test
    public void formatDate() {
        System.out.println(DateUtils.formatDateTime(new Date()));
    }

    @Test
    public void dateToLocalDateTime() {
        System.out.println(DateUtils.dateToLocalDateTime(new Date()));
    }

    @Test
    public void localDateTimeToDate() {
        System.out.println(DateUtils.localDateTimeToDate(LocalDateTime.now()));
    }

    @Test
    public void localDateTimeFormat() {
        System.out.println(DateUtils.localDateTimeFormat(LocalDateTime.now()));
    }

    @Test
    public void localDateFormat() {
        System.out.println(DateUtils.localDateFormat(LocalDateTime.now()));
    }

    @Test
    public void DateToSeconds() {
        System.out.println(DateUtils.DateToSeconds(new Date()));
    }

    @Test
    public void secondsToDate() {
        System.out.println(DateUtils.secondsToDate(1597300225));
    }

    @Test
    public void mills() {
        System.out.println(DateUtils.mills());
    }

    @Test
    public void DateToMills() {
        System.out.println(DateUtils.DateToMills(new Date()));
    }

    @Test
    public void millsToDate() {
        System.out.println(DateUtils.millsToDate(1597300289327L));
    }

    @Test
    public void utcStringToLocalDateTime() {
        System.out.println(DateUtils.utcStringToLocalDateTime("2022-11-30T17:42:14+08:00"));
    }

    @Test
    public void localDateTimeToUtcString() {
        System.out.println(DateUtils.localDateTimeToUtcString(LocalDateTime.now()));
    }
}
