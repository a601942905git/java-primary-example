package com.primary.example.thread.collection;

import org.junit.Test;

import java.util.HashMap;

/**
 * com.primary.example.thread.collection.HashMapTest
 *
 * @author lipeng
 * @date 2019/12/29 下午8:28
 */
public class HashMapTest {

    public static final Integer THREAD_NUMBER = 100;

    @Test
    public void test() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_NUMBER];
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new Thread(new Task(hashMap));
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i].join();
        }
        System.out.println(hashMap);
    }

    public static class Task implements Runnable {
        private HashMap<String, Integer> hashMap;

        public Task(HashMap<String, Integer> hashMap) {
            this.hashMap = hashMap;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                hashMap.put(String.valueOf((i + 1)), (i + 1));
            }
        }
    }
}
