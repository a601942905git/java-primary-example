package com.primary.example.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * com.primary.example.string.StringJoinTest
 *
 * @author lipeng
 * @date 2019-03-01 17:14
 */
public class StringJoinDemo {

    public static void main(String[] args) {
        // a-b-c
        System.out.println(String.join("-", "a", "b", "c"));

        // 1,2,3
        System.out.println(String.join(",", Arrays.asList("1", "2", "3")));

        // jack&allen&lucky
        StringJoiner stringJoiner = new StringJoiner("&");
        stringJoiner.add("jack");
        stringJoiner.add("allen");
        stringJoiner.add("lucky");
        System.out.println(stringJoiner.toString());

        // ^smile%world%hello$
        stringJoiner = new StringJoiner("%", "^", "$");
        stringJoiner.add("smile");
        stringJoiner.add("world");
        stringJoiner.add("hello");
        System.out.println(stringJoiner.toString());

        // 1#2#3
        List<String> stringList = new ArrayList(){{
            add("1"); add("2"); add("3");}};

        System.out.println(stringList.stream().collect(Collectors.joining("#")));

    }
}
