package com.primary.example.util.test;

import com.primary.example.util.RetryUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * com.primary.example.util.test.RetryUtilsTest
 *
 * @author lipeng
 * @date 2020/7/14 3:52 PM
 */
public class RetryUtilsTest {

    @Test
    public void test() {
        RetryUtils.retryOnException(3, () -> {
            throw new NullPointerException();
        }, 1, TimeUnit.SECONDS);
    }
}
