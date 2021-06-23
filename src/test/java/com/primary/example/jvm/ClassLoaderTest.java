package com.primary.example.jvm;

import org.springframework.util.StringUtils;
import sun.util.resources.cldr.en.CalendarData_en_SG;

/**
 * com.primary.example.jvm.ClassLoaderTest
 *
 * 类加载器分类：
 *  1.引导类加载器，由C和C++实现，嵌入在JVM中，加载JAVA核心类库，比如String
 *  2.自定义加载器，由JAVA实现，继承ClassLoader
 *      2.1 扩展类加载器，加载java.ext.dirs路径下面的类
 *      2.2 系统类加载器，加载用户自定义类
 *
 * @author lipeng
 * @date 2021/6/23 9:58 AM
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        // 获取系统类加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemClassLoader);

        // 获取扩展类加载器：sun.misc.Launcher$ExtClassLoader@2eafffde
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("扩展类加载器：" + extClassLoader);

        // 获取引导类加载器：null，引导类加载器由C和C++实现，嵌入在JVM中，无法获取
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("引导类加载器：" + bootstrapClassLoader);

        // 获取加载ClassLoaderTest的类加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
        // 系统类加载器用于加载用户自定义的类
        ClassLoader classLoader1 = ClassLoaderTest.class.getClassLoader();
        System.out.println("加载ClassLoaderTest的类加载器：" + classLoader1);

        // 获取扩展路径
        String extDir = System.getProperty("java.ext.dirs");
        String[] extDirs = StringUtils.delimitedListToStringArray(extDir, ":");
        for (String dir : extDirs) {
            System.out.println("扩展路径：" + dir);
        }

        // 获取加载CalendarData_en_SG的类加载器：sun.misc.Launcher$ExtClassLoader@2eafffde
        // 用于加载扩展路径下面的类
        ClassLoader classLoader2 = CalendarData_en_SG.class.getClassLoader();
        System.out.println("加载CalendarData_en_SG的类加载器：" + classLoader2);

        // 获取加载String的类加载器：null
        // 引导类加载器用于加载JAVA的核心类库
        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println("加载String的类加载器：" + classLoader3);
    }
}
