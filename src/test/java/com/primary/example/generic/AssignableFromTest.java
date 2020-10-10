package com.primary.example.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * com.primary.example.generic.AssignableFromTest
 *
 * @author lipeng
 * @date 2020/10/10 4:49 PM
 */
@Slf4j
public class AssignableFromTest {

    /**
     * A与B相同或者A是B的父类或者父接口
     */
    @Test
    public void test() {
        log.info("result："+ Student.class.isAssignableFrom(Student.class));
        log.info("result：" + Student.class.isAssignableFrom(Person.class));
        log.info("result：" + Person.class.isAssignableFrom(Person.class));
        log.info("result：" + Person.class.isAssignableFrom(Student.class));
    }
}
