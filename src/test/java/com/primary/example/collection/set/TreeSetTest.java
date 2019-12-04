package com.primary.example.collection.set;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * com.primary.example.collection.set.TreeSetTest
 *
 * 无序，元素唯一，可以进行排序
 * @author lipeng
 * @date 2019-04-16 13:24
 */
public class TreeSetTest {

    @Test
    public void treeSetTest() {
        TreeSet<Person> treeSet = new TreeSet<>(Comparator.comparing(Person::getName, String::compareTo));
        treeSet.add(new Person(10001, "jack"));
        treeSet.add(new Person(10002, "lucky"));
        treeSet.add(new Person(10003, "bob"));
        treeSet.add(new Person(10004, "zone"));
        treeSet.add(new Person(10005, "smile"));
        treeSet.add(new Person(10001, "alone"));
        treeSet.stream().forEach(System.out::println);
    }
}
