package com.primary.example.jvm;

/**
 * com.primary.example.jvm.StringTest
 *
 * 双亲委派机制：
 * 1.当类加载器要加载一个类时，并不会立刻去加载，而是向上委托
 * 2.委托的加载器可以加载，就进行加载
 * 3.委托的加载器不可以加载，就由下层的加载器进行加载
 *
 * 引导类加载器
 *    ^
 *    |向上委托
 * 扩展类加载器
 *    ^
 *    |向上委托
 * 系统类加载器
 *
 * 加载java.lang.String类的时候一直委托到引导类加载器，引导类加载器用于加载JAVA核心类库
 * 可以加载java.lang.String类，因此自定义java.lang.String中的static模块不会被执行，main()方法执行报错
 *
 * @author lipeng
 * @date 2021/6/24 2:37 PM
 */
public class StringTest {

    static {
        System.out.println("自定义java.lang.StringTest 被执行");
    }

    public static void main(String[] args) {
        // 执行后可以发现自定义String中的static模块并没有执行
        ClassLoader classLoader = java.lang.String.class.getClassLoader();
        System.out.println("load String classLoader：" + classLoader);

        StringTest.class.getClassLoader();
    }
}
