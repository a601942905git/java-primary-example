package com.primary.example.util.test;

import com.primary.example.util.StreamUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * com.primary.example.util.test.StreamUtilsTest
 *
 * @author lipeng
 * @date 2020/1/7 下午2:25
 */
public class StreamUtilsTest {

    private List<User> userList = new ArrayList<>();

    @Before
    public void init() {
        userList.add(User.builder().id(10001).name("jack").age(32).build());
        userList.add(User.builder().id(10002).name("alone").age(24).build());
        userList.add(User.builder().id(10003).name("bob").age(18).build());
        userList.add(User.builder().id(10004).name("lucky").age(26).build());
        userList.add(User.builder().id(10005).name("cindy").age(22).build());
        userList.add(null);
    }

    @Test
    public void getList() {
        List<Integer> idList = StreamUtils.getList(userList, User::getId);
        StreamUtils.println(idList);
    }

    @Test
    public void filter() {
        List<User> users = StreamUtils.filter(userList, user -> user.getAge() > 30);
        StreamUtils.println(users);
    }

    @Test
    public void map() {
        List<String> names = StreamUtils.map(userList, user -> StringUtils.upperCase(user.getName()));
        StreamUtils.println(names);
    }

    @Test
    public void sort() {
        System.out.println("=======排序前=======");
        StreamUtils.println(userList);
        List<User> users = StreamUtils.sort(userList, user -> user.getAge());
        System.out.println("=======排序后=======");
        StreamUtils.println(users);
    }

    @Test
    public void reversed() {
        System.out.println("=======排序前=======");
        StreamUtils.println(userList);
        List<User> users = StreamUtils.reversed(userList, user -> user.getAge());
        System.out.println("=======排序后=======");
        StreamUtils.println(users);
    }
}
