package com.primary.example.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * com.primary.example.cache.LRULinkedHashMap
 *
 * 思路：
 * 1. 继承LinkedHashMap
 * 2. 通过构建函数设置元素访问后的执行策略以及设置初始容量
 * 3. 重写removeEldestEntry()方法
 * @author lipeng
 * @date 2021/5/19 11:23 AM
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRULinkedHashMap(int capacity) {
        // 初始大小，0.75是装载因子，true是表示按照访问时间排序
        // 第三个参数设置为true，元素被访问后会放在链表尾部
        super(capacity, 0.75f, true);
        // 传入指定的缓存最大容量
        this.capacity = capacity;
    }

    /**
     * 实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
