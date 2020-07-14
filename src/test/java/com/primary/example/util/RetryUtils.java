package com.primary.example.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * com.primary.example.util.RetryUtils
 *
 * @author lipeng
 * @date 2020/7/14 3:49 PM
 */
@Slf4j
public class RetryUtils {

    /**
     * 在遇到异常时进行重试
     *
     * @param retryLimit 重试次数
     * @param retrySupplier 重试执行的任务
     * @param timeInterval 时间间隔
     * @param timeUnit 时间间隔单位
     * @param <T> 返回值类型
     * @return 任务返回值
     */
    public static <T> T retryOnException(int retryLimit, Supplier<T> retrySupplier,
                                         int timeInterval, TimeUnit timeUnit) {

        T result = null;
        try {
            result = retrySupplier.get();
        } catch (Exception e) {
            log.warn("ready to retry on exception：", e);
            for (int i = 0; i < retryLimit; i++) {
                try {
                    timeUnit.sleep(timeInterval * (i + 1));
                    result = retrySupplier.get();
                } catch (InterruptedException e1) {
                    Thread.currentThread().interrupt();
                    log.error("thread interrupted exception：", e1);
                } catch (Exception e1) {
                    if (i >= retryLimit - 1) {
                        log.error("retry on " + (i + 1) + " times, exception is: ", e1);
                    } else {
                        log.warn("retry on " + (i + 1) + " times, exception is: ", e1);
                    }
                }

                if (Objects.nonNull(result)) {
                    break;
                }
            }
        }
        return result;
    }
}
