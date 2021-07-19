package com.primary.example.jvm.string;

import org.junit.Test;

/**
 * com.primary.example.jvm.string.StringNewTest
 *
 * @author lipeng
 * @date 2021/7/19 10:02 AM
 */
public class StringNewTest {

    /**
     * public void test();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=4, locals=3, args_size=1
     *          0: new           #2                  // class java/lang/String
     *          3: dup
     *          4: ldc           #3                  // String a
     *          6: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
     *          9: astore_1
     *         10: new           #5                  // class java/lang/StringBuilder
     *         13: dup
     *         14: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
     *         17: new           #2                  // class java/lang/String
     *         20: dup
     *         21: ldc           #7                  // String b
     *         23: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
     *         26: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *         29: new           #2                  // class java/lang/String
     *         32: dup
     *         33: ldc           #9                  // String c
     *         35: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
     *         38: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *         41: invokevirtual #10                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *         44: astore_2
     *         45: return
     *       LineNumberTable:
     *         line 15: 0
     *         line 16: 10
     *         line 17: 45
     *       LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             0      46     0  this   Lcom/primary/example/jvm/string/StringNewTest;
     *            10      36     1     a   Ljava/lang/String;
     *            45       1     2     d   Ljava/lang/String;
     *     RuntimeVisibleAnnotations:
     *       0: #25()
     */
    @Test
    public void test1() {
        /**
         * 创建2个对象
         *  1.在堆中创建String对象
         *  2.在常量池中创建字符串"a"
         */
        String a = new String("a");

        /**
         * 创建6个对象
         *  1.创建StringBuilder对象
         *  2.new String("b")创建2个对象
         *  3.new String("c")创建2个对象
         *  4.StringBuilder的toString()创建1个String对象
         *
         */
        String d = new String("b") + new String("c");
    }

    @Test
    public void test2() {
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2);


        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
