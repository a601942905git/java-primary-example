package com.primary.example.util.test;

import com.primary.example.util.StreamCheckedExceptionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.wolf.heart.exception.StreamCheckedExceptionTest
 *
 * @author lipeng
 * @date 2019-01-30 16:00
 */
public class StreamCheckedExceptionTest {

    @Test
    public void test() {
        List<String> nameList = new ArrayList<String>() {{
            add("jack");
            add("jone");
            add(null);
        }};
        nameList = nameList.stream()
                .map(StreamCheckedExceptionUtils.wrapException(String::toUpperCase, item -> "DEFAULT"))
                .collect(Collectors.toList());
        System.out.println(nameList);
    }
}
