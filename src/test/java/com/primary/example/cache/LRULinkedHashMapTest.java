package com.primary.example.cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * com.primary.example.cache.LRULinkedHashMapTest
 *
 * @author lipeng
 * @date 2021/5/19 11:40 AM
 */
@Slf4j
public class LRULinkedHashMapTest {

    @Test
    public void test() {
        LRULinkedHashMap<String, String> lruLinkedHashMap = new LRULinkedHashMap<>(3);
        lruLinkedHashMap.put("key1", "value1");
        lruLinkedHashMap.put("key2", "value2");
        lruLinkedHashMap.put("key3", "value3");

        lruLinkedHashMap.get("key2");
        lruLinkedHashMap.get("key3");
        lruLinkedHashMap.get("key1");

        lruLinkedHashMap.put("key4", "value4");
        log.info("lruLinkedHashMap------>" + lruLinkedHashMap);
    }
}
