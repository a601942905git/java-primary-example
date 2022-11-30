package com.primary.example.util;

import org.springframework.util.Assert;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * com.primary.example.util.DateUtils
 *
 * @author lipeng
 * @date 2020/8/12 7:42 PM
 */
public class DateUtils {

    private static final String DEFAULT_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static String formatDateTime(Date date) {
        Assert.notNull(date, "param date is null");
        return localDateTimeFormat(dateToLocalDateTime(date), DEFAULT_DATE_TIME_FORMAT_PATTERN);
    }

    public static String formatDate(Date date) {
        Assert.notNull(date, "param date is null");
        return localDateTimeFormat(dateToLocalDateTime(date), DEFAULT_DATE_FORMAT_PATTERN);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Assert.notNull(date, "param date is null");
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "param localDateTime is null");
        return Date.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant());
    }

    public static String localDateTimeFormat(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "localDateTime is null");
        return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT_PATTERN));
    }

    public static String localDateFormat(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "localDateTime is null");
        return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT_PATTERN));
    }

    private static String localDateTimeFormat(LocalDateTime localDateTime, String formatPattern) {
        Assert.notNull(localDateTime, "localDateTime is null");
        return localDateTime.format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static long mills() {
        return Clock.systemDefaultZone().millis();
    }

    public static int DateToSeconds(Date date) {
        Assert.notNull(date, "param date is null");
        return (int) (date.getTime() / 1000);
    }

    public static Date secondsToDate(int second) {
        return new Date(second * 1000L);
    }

    public static long DateToMills(Date date) {
        Assert.notNull(date, "param date is null");
        return date.getTime();
    }

    public static Date millsToDate(long mills) {
        return new Date(mills);
    }

    public static LocalDateTime utcStringToLocalDateTime(String utcString) {
        Assert.hasLength(utcString, "utc string is empty");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(utcString);
        Instant instant = zonedDateTime.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static String localDateTimeToUtcString(LocalDateTime localDateTime) {
        Assert.notNull(localDateTime, "local date time is null");
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
