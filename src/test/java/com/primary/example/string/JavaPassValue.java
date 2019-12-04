package com.primary.example.string;

import org.junit.Test;

/**
 * com.primary.example.string.JavaPassValue
 * Java传值问题
 * @author lipeng
 * @date 2019-03-19 10:11
 */
public class JavaPassValue {

    @Test
    public void test1() {
        int a = 10;
        System.out.println("before change，value：" + a);
        changeValue(a);
        System.out.println("after change，value：" + a);
    }

    private void changeValue(int a) {
        a = 20;
    }

    /**
     * 从test2和test3可以看出Java是按值传递
     * 如果是按引用传递的话，那么test3执行完成之后内容应该发生改变
     *
     * java值传递
     * 针对栈中的值，拷贝值本身
     * 针对堆中的值，拷贝地址值
     */
    @Test
    public void test2() {
        Student student = new Student();
        student.setId(10001);
        student.setName("jack");
        System.out.println("before change，student：" + student);
        changeStudent(student);
        System.out.println("after change，student：" + student);
    }

    private void changeStudent(Student student) {
        student.setName("lucky");
    }

    @Test
    public void test3() {
        Student student = new Student();
        student.setId(10001);
        student.setName("jack");
        System.out.println("before change，student：" + student);
        changeStudent1(student);
        System.out.println("after change，student：" + student);
    }

    private void changeStudent1(Student student) {
        student = new Student();
        student.setId(20002);
        student.setName("allen");
    }

    @Test
    public void test4() {
        String str = "jack";
        System.out.println("before change，str：" + str);
        changeStr(str);
        System.out.println("after change，str：" + str);
    }

    /**
     * 通过查看String的源码，可以看到里面定义的char[]是final，也就是不可变的
     * 每次对字符串的操作都相当于new String()操作
     * @param str
     */
    private void changeStr(String str) {
        str = "smile";
    }
}
