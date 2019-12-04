package com.primary.example.collection.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * com.primary.example.collection.map.HashMapTest
 *
 * @author lipeng
 * @date 2019-05-07 09:33
 */
public class HashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        map.put("key6", "value6");
        map.put("key7", "value7");
        map.put("key8", "value8");
        map.put("key9", "value9");
        map.put("key10", "value10");
        map.put("key11", "value11");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=======>" + entry.getValue());
        }
    }
}
