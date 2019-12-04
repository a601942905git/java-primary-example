package com.primary.example.collection.set;

import java.util.LinkedHashSet;

/**
 * com.primary.example.collection.set.LinkedHashSet
 *
 * LinkedHashSet有序，元素不能重复
 * @author lipeng
 * @date 2019-04-16 13:11
 */
public class LinkedHashSetTest {

    public static void main(String[] args) {
        LinkedHashSet<Person> personLinkedHashSet = new LinkedHashSet<Person>();
        personLinkedHashSet.add(new Person(10001, "smile"));
        personLinkedHashSet.add(new Person(10002, "jack"));
        personLinkedHashSet.add(new Person(10003, "lucky"));
        personLinkedHashSet.add(new Person(10004, "lily"));
        personLinkedHashSet.add(new Person(10005, "alone"));
        personLinkedHashSet.add(new Person(10002, "jack"));
        personLinkedHashSet.stream().forEach(System.out::println);
    }
}
