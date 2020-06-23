package com.primary.example.util;

import java.util.ArrayList;
import java.util.List;

/**
 * com.primary.example.util.BeanUtils
 *
 * @author lipeng
 * @date 2020/6/23 7:02 PM
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>(sourceList.size());
        for (S s : sourceList) {
            T target = org.springframework.beans.BeanUtils.instantiateClass(targetClass);
            copyProperties(s, target);
            targetList.add(target);
        }
        return targetList;
    }
}
