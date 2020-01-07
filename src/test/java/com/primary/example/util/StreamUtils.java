package com.primary.example.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * com.primary.example.util.StreamUtils
 *
 * @author lipeng
 * @date 2020/1/7 下午2:19
 */
public class StreamUtils {

    private static <T> Stream<T> streamOf(Collection<T> collection) {
        return null == collection || collection.size() == 0 ? Stream.empty() : collection.stream();
    }

    /**
     * 移除集合中为null的元素
     *
     * @param list 集合对象
     * @param <T> 集合中元素数据类型
     * @return 集合对象
     */
    private static <T> Stream<T> removeNullElement(List<T> list) {
        return StreamUtils.streamOf(list).filter(Objects::nonNull);
    }

    /**
     * 获取集合某一属性集合
     *
     * @param list 集合对象
     * @param mapper 集合中每个元素需要执行的function
     * @param <T> 参数集合中元素数据类型
     * @param <R> 返回集合中元素数据类型
     * @return 执行完函数后的集合
     */
    public static <T, R> List<R> getList(List<T> list, Function<? super T, ? extends R> mapper) {
        return removeNullElement(list).map(mapper).collect(Collectors.toList());
    }

    /**
     * 过滤集合
     *
     * @param list 集合对象
     * @param predicate 集合中每个元素需要执行的过滤方法
     * @param <T> 集合中元素数据类型
     * @return 过滤后的集合对象
     */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        return removeNullElement(list).filter(predicate).collect(Collectors.toList());
    }

    /**
     * 获取集合某一属性集合
     *
     * @param list 集合对象
     * @param mapper 集合中每个元素需要执行的function
     * @param <T> 参数集合中元素数据类型
     * @param <R> 返回集合中元素数据类型
     * @return 执行完函数后的集合
     */
    public static <T, R> List<R> map(List<T> list, Function<? super T, ? extends R> mapper) {
        return removeNullElement(list).map(mapper).collect(Collectors.toList());
    }

    /**
     * 对集合进行升序排序
     *
     * @param list 集合对象
     * @param keyExtractor 集合中每个元素需要执行的function
     * @param <T> 参数集合中元素数据类型
     * @param <U> 实现Comparable接口的类数据类型
     * @return 排序后的集合对象
     */
    public static <T, U extends Comparable<? super U>> List<T> sort(
            List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return removeNullElement(list).sorted(Comparator.comparing(keyExtractor)).collect(Collectors.toList());
    }

    /**
     * 对集合进行降序排序
     *
     * @param list 集合对象
     * @param keyExtractor 集合中每个元素需要执行的function
     * @param <T> 参数集合中元素数据类型
     * @param <U> 实现Comparable接口的类数据类型
     * @return 排序后的集合对象
     */
    public static <T, U extends Comparable<? super U>> List<T> reversed(
            List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return removeNullElement(list).sorted(Comparator.comparing(keyExtractor).reversed()).collect(Collectors.toList());
    }

    /**
     * 打印集合中的元素
     *
     * @param list 集合对象
     * @param <T> 集合中元素数据类型
     */
    public static <T> void println(List<T> list) {
        streamOf(list).forEach(System.out::println);
    }
}
