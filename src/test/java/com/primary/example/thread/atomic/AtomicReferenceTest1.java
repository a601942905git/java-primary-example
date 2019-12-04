package com.primary.example.thread.atomic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * com.primary.example.thread.atomic.AtomicReferenceTest1
 *
 * AtomicReference原子更新引用对象，只有对象的地址发生改变才算是被更新过
 *
 * @author lipeng
 * @date 2019/11/26 上午10:23
 */
public class AtomicReferenceTest1 {

    private static final AtomicReference<User> USER_ATOMIC_REFERENCE = new AtomicReference<>();

    @Test
    public void test() {
        User originUser = User.builder()
                .id(10001)
                .name("jack")
                .build();
        USER_ATOMIC_REFERENCE.set(originUser);

        User updateUser = User.builder()
                .id(10002)
                .name("smith")
                .build();
        USER_ATOMIC_REFERENCE.compareAndSet(originUser, updateUser);
        System.out.println(USER_ATOMIC_REFERENCE.get());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class User {

        private Integer id;

        private String name;
    }
}
