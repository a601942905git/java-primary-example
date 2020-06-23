package com.primary.example.util.test;

import com.primary.example.util.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * com.primary.example.util.test.BeanUtilsTest
 *
 * @author lipeng
 * @date 2020/6/23 7:08 PM
 */
public class BeanUtilsTest {

    @Test
    public void testCopyProperties() {
        User sourceUser = User.builder()
                .id(10001)
                .name("source")
                .build();
        Person person = new Person();
        BeanUtils.copyProperties(sourceUser, person);
        Assert.assertEquals(sourceUser.getId(), person.getId());
        Assert.assertEquals(sourceUser.getName(), person.getName());
    }

    @Test
    public void testCopyList() {
        List<User> userList = getUserList();
        List<Person> personList = BeanUtils.copyList(userList, Person.class);
        System.out.println(personList);
    }

    private List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        User sourceUser1 = User.builder()
                .id(10001)
                .age(21)
                .name("source1")
                .build();
        User sourceUser2 = User.builder()
                .id(10002)
                .age(22)
                .name("source2")
                .build();
        User sourceUser3 = User.builder()
                .id(10003)
                .age(23)
                .name("source3")
                .build();
        User sourceUser4 = User.builder()
                .id(10004)
                .age(24)
                .name("source4")
                .build();
        User sourceUser5 = User.builder()
                .id(10005)
                .age(25)
                .name("source5")
                .build();
        userList.add(sourceUser1);
        userList.add(sourceUser2);
        userList.add(sourceUser3);
        userList.add(sourceUser4);
        userList.add(sourceUser5);
        return userList;
    }
}
