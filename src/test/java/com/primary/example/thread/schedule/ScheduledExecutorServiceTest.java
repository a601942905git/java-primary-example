package com.primary.example.thread.schedule;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * com.primary.example.thread.schedule.ScheduledExecutorServiceTest
 *
 * @author lipeng
 * @date 2020/10/21 4:19 PM
 */
@Slf4j
public class ScheduledExecutorServiceTest {

    /**
     * 不受业务执行时间的影响
     * 假如第一次的指定时间：2020-10-21 16:36:19，业务执行耗时1s
     * 下一次的执行时间：2020-10-21 16:36:21
     * 下下一次的执行时间：2020-10-21 16:36:23
     * @throws InterruptedException
     */
    @Test
    public void scheduleAtFixedRateTest() throws InterruptedException {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                () -> {
                    log.info("current date======>" + new Date());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, 2, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 受业务执行时间的影响
     * 假如第一次的指定时间：2020-10-21 16:36:19，业务执行耗时1s
     * 下一次的执行时间：2020-10-21 16:36:22
     * 下下一次的执行时间：2020-10-21 16:36:25
     * @throws InterruptedException
     */
    @Test
    public void scheduleWithFixedDelayTest() throws InterruptedException {
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(
                () -> {
                    log.info("current date======>" + new Date());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, 2, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);
    }
}
