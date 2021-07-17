package com.primary.example.jvm.string;

import org.junit.Test;

/**
 * com.primary.example.jvm.string.StringEqualsTest
 *
 * 1.字符串、使用final修饰的String变量相加会被编译器优化
 * 2.+号中出现一个变量底层相当于创建了一个StringBuilder进行append()操作，最终调用toString()
 *
 * @author lipeng
 * @date 2021/7/17 10:07 AM
 */
public class StringEqualsTest {

    @Test
    public void test1() {
        String a = "helloworld";
        String b = "helloworld";
        // true
        System.out.println(a == b);
        // true
        System.out.println(a.equals(b));
    }

    /**
     * 编译后的代码
     *
     *     @Test
     *     public void test2() {
     *         String a = "helloworld";
     *         String b = "helloworld";
     *         System.out.println(a == b);
     *         System.out.println(a.equals(b));
     *     }
     */
    @Test
    public void test2() {
        String a = "hello" + "world";
        String b = "helloworld";
        // true
        System.out.println(a == b);
        // true
        System.out.println(a.equals(b));
    }

    /**
     * 反编译后内容
     *
     * public void test3();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=3, locals=4, args_size=1
     *          0: ldc           #6                  // String hello
     *          2: astore_1
     *          3: new           #7                  // class java/lang/StringBuilder
     *          6: dup
     *          7: invokespecial #8                  // Method java/lang/StringBuilder."<init>":()V
     *         10: aload_1
     *         11: invokevirtual #9                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *         14: ldc           #10                 // String world
     *         16: invokevirtual #9                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *         19: invokevirtual #11                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *         22: astore_2
     *         23: ldc           #2                  // String helloworld
     *         25: astore_3
     *         26: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *
     *
     */
    @Test
    public void test3() {
        String a = "hello";
        String b = a + "world";
        String c = "helloworld";
        // false
        System.out.println(b == c);
        // true
        System.out.println(b.equals(c));
    }

    /**
     * 编译后的代码
     * @Test
     *     public void test4() {
     *         String a = "hello";
     *         String b = "world";
     *         String c = "helloworld";
     *         String d = "helloworld";
     *         System.out.println(c == d);
     *         System.out.println(c.equals(d));
     *     }
     */
    @Test
    public void test4() {
        final String a = "hello";
        final String b = "world";
        String c = a + b;
        String d = "helloworld";
        // true
        System.out.println(c == d);
        // true
        System.out.println(c.equals(d));
    }
}
