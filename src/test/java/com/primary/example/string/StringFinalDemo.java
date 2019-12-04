package com.primary.example.string;

/**
 * com.primary.example.string.StringFinalTest
 *
 * @author lipeng
 * @date 2019-01-31 13:19
 */
public class StringFinalDemo {

    /**
     * 常量池在java用于保存在编译期已确认，已编译class文件中的一份数据
     * @param args
     */
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        String a = "helloWorld";
        String b = "hello";
        final String c = "hello";
        String d = b + "World";
        String e = c + "World";
        String f = "helloWorld";

        System.out.println(a == d);
        System.out.println(a == e);
        System.out.println(a == f);
    }
}
