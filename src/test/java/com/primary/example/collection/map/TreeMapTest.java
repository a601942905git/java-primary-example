package com.primary.example.collection.map;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * com.primary.example.collection.map.TreeMap
 * 可以对key进行排序
 * @author lipeng
 * @date 2019-05-08 11:31
 */
public class TreeMapTest {

    @Test
    public void test() {
        TreeMap<Integer, String> treeMap = new TreeMap<>(Integer::compareTo);
        treeMap.put(8, "smile");
        treeMap.put(2, "jack");
        treeMap.put(12, "wids");
        treeMap.put(6, "alone");
        treeMap.put(66, "zone");

        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + "======>" + entry.getValue());
        }
    }
}
