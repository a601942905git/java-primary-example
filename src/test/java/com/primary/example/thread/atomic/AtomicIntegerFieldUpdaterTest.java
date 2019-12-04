package com.primary.example.thread.atomic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * com.primary.example.thread.atomic.AtomicIntegerFieldUpdaterTest
 *
 * @author lipeng
 * @date 2019/11/26 上午11:11
 */
public class AtomicIntegerFieldUpdaterTest {

    private static final AtomicIntegerFieldUpdater<User> USER_ATOMIC_INTEGER_FIELD_UPDATER =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "id");

    @Test
    public void test() {
        User user = User.builder()
                .id(10001)
                .name("jack")
                .build();
        System.out.println("update result：" + USER_ATOMIC_INTEGER_FIELD_UPDATER.compareAndSet(user, 10001, 10002));
        System.out.println("update result：" + USER_ATOMIC_INTEGER_FIELD_UPDATER.compareAndSet(user, 10003, 10001));
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {

        public volatile int id;

        public String name;
    }
}

