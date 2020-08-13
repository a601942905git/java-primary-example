package com.primary.example.collection.map;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * com.primary.example.collection.map.LinkedHashMap
 * LinkedHashMap内部维护了一个双向链，可以保证输出的顺序和添加的顺序一致
 * @author lipeng
 * @date 2019-05-08 11:21
 */
public class LinkedHashMapTest {

    @Test
    public void test() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("key1", 1);
        linkedHashMap.put("key2", 2);
        linkedHashMap.put("key3", 3);
        linkedHashMap.put("key4", 4);
        linkedHashMap.put("key5", 5);

        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + "======>" + entry.getValue());
        }

        linkedHashMap.forEach((key, value) -> System.out.println(key + "===>" + value));
    }
}
