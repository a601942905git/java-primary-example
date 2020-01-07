package com.primary.example.util;

import com.primary.example.stream.exception.CheckedExceptionFunction;

import java.util.function.Function;

/**
 * com.wolf.heart.exception.StreamCheckedExceptionUtils
 *
 * @author lipeng
 * @date 2019-01-30 15:54
 */
public class StreamCheckedExceptionUtils {

    public static <T, R> Function<T, R> wrapException(
            CheckedExceptionFunction<T, R> checkedExceptionFunction, Function<T, R> function) {
        return t -> {
            try {
                return checkedExceptionFunction.apply(t);
            } catch (Exception e) {
                return function.apply(t);
            }
        };
    }
}
