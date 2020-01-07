package com.primary.example.util.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * apache.common.utils.StringUtils
 * String 操作工具类
 * 使用StringUtils工具类的原因：
 * 1、null safe
 * 2、方法直接调用、代码简洁
 *
 * @author lipeng
 * @dateTime 2018/8/9 上午9:25
 */
public class StringUtilsTest {

    @Test
    public void differenceMethod() {
        // 返回2个字符串之间的不同，以第二个参数为准,结果为ghd
        System.out.println(StringUtils.difference("abcdef", "abcdefghd"));

        // 结果为xyz
        System.out.println(StringUtils.difference("abc", "xyz"));

        // 找出不同处的下标位置
        System.out.println(StringUtils.indexOfDifference("abcd", "abz"));

        // 比较所有的的，找出不同处的下标位置
        System.out.println(StringUtils.indexOfDifference(new String[]{"abcd", "aijk", "xyz"}));
    }


    @Test
    public void reverseMethod() {
        // shift > 0,结果为fgabcde
        System.out.println(StringUtils.rotate("abcdefg", 2));

        // shift < 0,结果为cdefgab
        System.out.println(StringUtils.rotate("abcdefg", -2));

        // 结果为gfedcba
        System.out.println(StringUtils.reverse("abcdefg"));

        // 结果为a#b#c#d
        System.out.println(StringUtils.reverseDelimited("a#b#c#d", '#'));
    }

    @Test
    public void caseMethod() {
        // 结果为ABCD
        System.out.println(StringUtils.upperCase("abcd"));

        // 结果为abcd
        System.out.println(StringUtils.lowerCase("ABCD"));

        // 大写转小写，小写转大写。结果为AbCd
        System.out.println(StringUtils.swapCase("aBcD"));

        // 首字母大写，结果为Abcd
        System.out.println(StringUtils.capitalize("aBCD"));

        // 首字母小写abcd
        System.out.println(StringUtils.uncapitalize("Abcd"));
    }

    @Test
    public void countMatchMethod() {
        // 结果为2
        System.out.println(StringUtils.countMatches("abcdabghfr", 'a'));

        // 结果为3
        System.out.println(StringUtils.countMatches("测试123测试234测试", "测试"));
    }

    @Test
    public void judgmentMethod() {
        // 结果为true
        System.out.println(StringUtils.isAlpha("abcd"));

        // 结果为false
        System.out.println(StringUtils.isAlpha("ab12cd"));

        // 结果为true
        System.out.println(StringUtils.isAlphanumeric("abcd234efg"));

        // 结果为false
        System.out.println(StringUtils.isNumeric("avb578fk"));

        // 结果为true
        System.out.println(StringUtils.isNumeric("123456"));

        // 结果为true
        System.out.println(StringUtils.isWhitespace(""));

        // 结果为true
        System.out.println(StringUtils.isWhitespace("  "));

        // 结果为false
        System.out.println(StringUtils.isWhitespace(" 123ba"));

        // 结果为false
        System.out.println(StringUtils.isWhitespace("12 3ba"));

        // 结果为false
        System.out.println(StringUtils.isWhitespace(" 123 ba "));
    }

    @Test
    public void appendStrMethod() {
        // 如果str中不是以suffixes其中任何一个结尾，那么将suffix追加到str末尾，结果为hello,java
        System.out.println(StringUtils.appendIfMissing("hello,", "java", new String[]{"javascript", "python", "lua", "mysql"}));
        // 结果为hello,lua
        System.out.println(StringUtils.appendIfMissing("hello,lua", "java", new String[]{"javascript", "python", "lua", "mysql"}));

        // 结果为hello,lua
        System.out.println(StringUtils.prependIfMissing(",lua", "hello", new String[]{"hi", "hell", "hel"}));
        // 结果为hello,lua
        System.out.println(StringUtils.prependIfMissing("hello,lua", "hello", new String[]{"hi", "hell", "hello"}));
    }

    @Test
    public void removeNewLineMethod() {
        /**
         * 移除字符串末尾的newLine,newLine包括\r、\n、\r\n，只移除一次
         */

        // 结果为hello,lua
        System.out.println(StringUtils.chomp("hello,lua\r"));
        // 结果为hello,lua
        System.out.println(StringUtils.chomp("hello,lua\n"));
        // 结果为hello,lua
        System.out.println(StringUtils.chomp("hello,lua\r\n"));
        // 结果为hello,lua\r\n
        System.out.println(StringUtils.chomp("hello,lua\r\n\r\n"));
        // 结果为hello,lua\n\r
        System.out.println(StringUtils.chomp("hello,lua\n\r"));

        System.out.println("=====================================");

        // 结果为hello,java
        System.out.println(StringUtils.chop("hello,java\r"));
        // 结果为hello,java
        System.out.println(StringUtils.chop("hello,java\n"));
        // 结果为hello,java
        System.out.println(StringUtils.chop("hello,java\r\n"));
        // 结果为hello,java\r\n
        System.out.println(StringUtils.chop("hello,java\r\n\r\n"));
        // 结果为hello,java\n\r
        System.out.println(StringUtils.chop("hello,java\n\r"));
        System.out.println("=====================================");
    }

    @Test
    public void replaceMethod() {
        // 结果为hello,redis
        System.out.println(StringUtils.replace("hello,java", "java", "redis"));

        // 结果为hello,redis.java is goods language
        System.out.println(StringUtils.replace("hello,java.java is goods language", "java", "redis", 1));

        // 结果为hello,redis.redis is goods language
        System.out.println(StringUtils.replace("hello,java.java is goods language", "java", "redis", 2));

        // 结果为hello,redis.redis is goods language
        System.out.println(StringUtils.replaceAll("hello,java.java is goods language", "java", "redis"));

        // 结果为yefw
        System.out.println(StringUtils.replaceEach("abefdc", new String[]{"ab", "dc"}, new String[]{"y", "w"}));

        // 结果为wefw
        System.out.println(StringUtils.replaceEachRepeatedly("abefdc", new String[]{"ab", "dc"}, new String[]{"dc", "w"}));

        // 结果为bbcadaeaf
        System.out.println(StringUtils.replaceFirst("bacadaeaf", "a", "b"));

        // 结果为bbcadaeaf
        System.out.println(StringUtils.replaceOnce("bacadaeaf", "a", "b"));

        /**
         * 覆盖字符串
         * 用overlay覆盖str从start到end之间的字符串，不包括end
         * start为负数，从0开始
         * end为负数，从0开始
         * 如果start > end交换值
         */
        System.out.println(StringUtils.overlay("abcdef", "zzzz", 2, 4));
    }

    /**
     * 处理字符串方法
     */
    @Test
    public void dealStrMethod() {
        // 如果字符串为null，则返回""，结果为""
        System.out.println(StringUtils.defaultString(null));
        // 结果为defaultStr
        System.out.println(StringUtils.defaultString(null, "defaultStr"));

        // 结果为emptyStr
        System.out.println(StringUtils.defaultIfEmpty(null, "emptyStr"));
        // 结果为emptyStr
        System.out.println(StringUtils.defaultIfEmpty("", "emptyStr"));

        // 结果为blankStr
        System.out.println(StringUtils.defaultIfBlank("   ", "blankStr"));
        // 结果为blankStr
        System.out.println(StringUtils.defaultIfBlank(null, "blankStr"));
        // 结果为blankStr
        System.out.println(StringUtils.defaultIfBlank("", "blankStr"));

        // 删除字符串前、后、中间的空格
        System.out.println(StringUtils.deleteWhitespace(" s m i l e "));

    }

    @Test
    public void removeStringMethod() {
        // 移除字符串中指定的字符，结果为smle
        System.out.println(StringUtils.remove("smiile", "i"));

        // 结果为welcome to you!!!
        System.out.println(StringUtils.remove("smilewelcome to you!!!", "smile"));

        // 结果为welcome to you!!!
        System.out.println(StringUtils.removeAll("smilewelcome to you!!!", "^smile"));

        // 移除第一个，结果为welcome to yousmile
        System.out.println(StringUtils.removeFirst("welcomesmile to yousmile", "smile"));
        // 移除最后一个，结果为smilewelcome to you
        System.out.println(StringUtils.removeEnd("smilewelcome to yousmile", "smile"));

        // 如果字符串是以smile开头的，那么移除，否则不进行移除
        System.out.println(StringUtils.removeStart("smilewelcome to yousmile", "smile"));
        System.out.println(StringUtils.removeStart("widswelcome to yousmile", "smile"));
    }

    @Test
    public void containsStringMethod() {
        // 结果为false
        System.out.println(StringUtils.contains("smile,wids", "SMILE"));

        // 结果为true
        System.out.println(StringUtils.containsIgnoreCase("smile,wids", "SMILE"));

        // 结果为true
        System.out.println(StringUtils.containsAny("smile,wids", new String[]{"smile", "wids", "jack"}));

        // smile不包含有jack中任何一个字符，结果为true
        System.out.println(StringUtils.containsNone("smile", "jack"));

        // 有点类似equals，第一个参数只有第二个参数，结果为true
        System.out.println(StringUtils.containsOnly("smile", "smile"));

        // 结果为false
        System.out.println(StringUtils.containsOnly("smile,jack", "smile"));

        // 判断字符串是否包含空格，前、中、后都判断，结果为false
        System.out.println(StringUtils.containsWhitespace("smile"));
        // 结果为true
        System.out.println(StringUtils.containsWhitespace(" smile"));
        // 结果为true
        System.out.println(StringUtils.containsWhitespace("smile "));
        // 结果为true
        System.out.println(StringUtils.containsWhitespace("sm ile"));
    }

    @Test
    public void indexOfStringMethod() {
        // 结果为6
        System.out.println(StringUtils.indexOf("Hello,world", "world"));

        // 从startPos开始检索目标字符串
        System.out.println(StringUtils.indexOf("Hello,world", "d", 6));

        // 结果为0
        System.out.println(StringUtils.indexOfAny("abcdefg", new String[]{"a", "b", "c", "d"}));

        // 搜索字符在指定字符串中第一次未出现的位置，结果为6
        System.out.println(StringUtils.indexOfAnyBut("abababcdced", "ab"));

        // 结果为4
        System.out.println(StringUtils.indexOfAnyBut("abcdefg", new char[]{'a', 'b', 'c', 'd'}));
    }

    /**
     * 字符串判空方法
     */
    @Test
    public void emptyStringMethod() {
        /**
         * isEmpty和isBlank大体方法一直
         * isEmpty认为空格也是一个字符
         * isBlank不认为空格是一个字符
         * isBlank只判定全部为空格的情况，不判定前后或者中间为空格的情况
         */
        // 结果为true
        System.out.println(StringUtils.isBlank(" "));
        // 结果为false
        System.out.println(StringUtils.isBlank("a c"));
        // 结果为false
        System.out.println(StringUtils.isBlank(" a c "));
    }

    /**
     * 字符串去除空格方法
     */
    @Test
    public void trimStringMethod() {
        // 去除头尾空格，结果为a b c
        System.out.println(StringUtils.trim(" a b c "));

        // 如果空串或者null，那么返回null，否则进行去空格操作，结果为null
        System.out.println(StringUtils.trimToNull(""));

        // 如果是null，返回空串，结果为""
        System.out.println("空字符串===>" + StringUtils.trimToEmpty(null));

        // 去除头尾空格,结果为abc
        System.out.println(StringUtils.strip(" abc "));

        // 去掉头尾特定的字符,结果为bc
        System.out.println(StringUtils.strip("abca", "a"));

        // 去掉变音符号，结果为eclair
        System.out.println(StringUtils.stripAccents("éclair"));

        String[] stripStrs = {" cabc", "cbdc ", " cecfc"};
        // 去除数组中所有元素头尾空格,结果为cabc、cbdc、cecfc
        Arrays.stream(StringUtils.stripAll(stripStrs)).forEach(System.out::println);
        // 去除数组中所有元素特定头尾元素，结果为 cab(头尾空格，没法去除c字符)、bdc （尾部为空格，没法去除c字符）、cecf
        Arrays.stream(StringUtils.stripAll(stripStrs, "c")).forEach(System.out::println);
    }

    /**
     * 截取字符串
     */
    @Test
    public void substringMethod() {
        String subStr = "hello, i am learning apache commons lang utils";
        // 结果为i am learning apache commons lang utils
        System.out.println(StringUtils.substring(subStr, 7));

        // 结果为i am learning
        System.out.println(StringUtils.substring(subStr, 7, 20));

        String subStr1 = "hello-i-am-smile";
        // 结果为hello
        System.out.println(StringUtils.substringBefore(subStr1, "-"));

        // 结果为hello-i-am
        System.out.println(StringUtils.substringBeforeLast(subStr1, "-"));

        // 结果为i-am-smile
        System.out.println(StringUtils.substringAfter(subStr1, "-"));

        // 结果为smile
        System.out.println(StringUtils.substringAfterLast(subStr1, "-"));

        String subStr2 = "Hello **jack**,Good luck to you!!!!";
        // 结果为jack
        System.out.println(StringUtils.substringBetween(subStr2, "**"));

        // 结果为jack**,Good luck to you
        System.out.println(StringUtils.substringBetween(subStr2, "**", "!"));

        String subStr3 = "(Hello,smile):(Hello,jack):(Hello,jim)";
        // 结果为Hello,smile、Hello,jack、Hello,jim
        Arrays.stream(StringUtils.substringsBetween(subStr3, "(", ")"))
                .forEach(System.out::println);

        // 结果为Hello
        System.out.println(StringUtils.left("Hello,java", 5));

        // 结果为java
        System.out.println(StringUtils.right("Hello,java", 4));

        // 从pos开始截取len长度的字符串，结果为java
        System.out.println(StringUtils.mid("Hello,java,utils", 6, 4));
    }

    /**
     * 字符串比较
     */
    @Test
    public void compareStringMethod() {
        // 结果为true
        System.out.println(StringUtils.equals("str", "str"));
        // 结果为false
        System.out.println(StringUtils.equals("null", "str"));
        // 结果为false
        System.out.println(StringUtils.equals("null", "str"));

        // 指定字符串是否和数组中的某个元素相等，结果为true
        System.out.println(StringUtils.equalsAny("str", new String[]{"str", "smile"}));

        // 以2个字符串最短的长度来循环比较2个字符串的每个字符，如果字符不相等，返回字符之间的差值
        // s位于第19位，j位于第9位，结果为9
        System.out.println(StringUtils.compare("smile", "jack"));
        // 结果为-1
        System.out.println(StringUtils.compare(null, "smile", true));
        // 结果为0
        System.out.println(StringUtils.compare(null, null, true));
        // 结果为1
        System.out.println(StringUtils.compare("smile", null, true));
    }

    /**
     * 字符串以什么开始，以什么结束方法类似
     */
    @Test
    public void startWithStringMethod() {
        // 结果为true
        System.out.println(StringUtils.startsWith("smilewidsjack", "smile"));
        // 结果为true
        System.out.println(StringUtils.startsWithAny("smilewidsjack", new String[]{"smile", "wids", "jack"}));
    }

    /**
     * 字符串拆分和拼接
     */
    @Test
    public void splitAndJoinStringMethod() {
        // 以空格进行拆分,结果为["jack", "smile", "lucky"]
        Arrays.stream(StringUtils.split("jack smile lucky")).forEach(System.out::println);

        System.out.println("============================================================");

        // 以逗号进行拆分,结果为["jack", "smile", "lucky"]
        Arrays.stream(StringUtils.split("jack,smile,lucky", ",")).forEach(System.out::println);

        System.out.println("============================================================");
        // 以逗号进行拆分，数组长度为1,结果为["jack,smile,lucky"]
        Arrays.stream(StringUtils.split("jack,smile,lucky", ",", 1)).forEach(System.out::println);

        System.out.println("============================================================");
        // 以逗号进行拆分，数组长度为2["jack", "smile,lucky"]
        Arrays.stream(StringUtils.split("jack,smile,lucky", ",", 2)).forEach(System.out::println);

        System.out.println("============================================================");
        // 以逗号进行拆分，数组长度为3["jack", "smile", "lucky"]
        Arrays.stream(StringUtils.split("jack,smile,lucky", ",", 3)).forEach(System.out::println);

        System.out.println("============================================================");
        // 拼接，结果为helloworld
        System.out.println(StringUtils.join(new String[]{"hello", "world"}));

        // 以"&"进行拼接，结果为hello&world
        System.out.println(StringUtils.join(new String[]{"hello", "world"}, "&"));

        // 以","从索引为2到索引为endIndex4为止进行拼接，结果为study,java,language
        System.out.println(StringUtils.join(new String[]{"hello", "world", "study", "java", "language"}, ",", 2, 5));
    }

}
