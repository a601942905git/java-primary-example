package com.primary.example.collection.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * com.primary.example.collection.set.SetTest
 * HashSet无序，元素不能重复
 *
 * @author lipeng
 * @date 2019-04-16 11:58
 */
public class HashSetTest {

    @Test
    public void setTest() {
        Set<Person> set = new HashSet<>();
        set.add(new Person(10001, "smile"));
        set.add(new Person(10002, "jack"));
        set.add(new Person(10003, "lucky"));
        set.add(new Person(10004, "lily"));
        set.add(new Person(10005, "alone"));
        set.add(new Person(10002, "jack"));
        set.stream().forEach(System.out::println);
    }
}
