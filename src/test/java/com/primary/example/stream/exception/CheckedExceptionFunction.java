package com.primary.example.stream.exception;

/**
 * com.wolf.heart.exception.CheckedExceptionFunction
 *
 * @author lipeng
 * @date 2019-01-30 15:53
 */
@FunctionalInterface
public interface CheckedExceptionFunction<T, R> {

    R apply(T t) throws Exception;
}
