package com.primary.example.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * com.primary.example.collection.ForeachAddAndRemoveElementTest
 * foreach中添加和删除元素
 * @author lipeng
 * @date 2019-03-06 09:43
 */
public class ForeachAddAndRemoveElementDemo {

    public static final String DELETE_NAME = "smile";

    public static void main(String[] args) {
        List<String> names = new ArrayList(){{
            add("jack");
            add("allen");
            add("smile");
            add("lucy");
            add("zone");
        }};

        names.stream().filter((name) -> !Objects.equals(name, DELETE_NAME)).forEach(System.out::println);
    }
}
