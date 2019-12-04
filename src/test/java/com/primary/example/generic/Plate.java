package com.primary.example.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * com.primary.example.generic.Palte
 * 盘子
 *
 * @author lipeng
 * @date 2019-07-18 14:15
 */
public class Plate<T> {

    private List<T> fruitList = new ArrayList<>();

    public Plate() {
    }

    public Plate(T item) {
        fruitList.add(item);
    }

    /**
     * 往盘子中放水果
     */
    public void put(T t) {
        fruitList.add(t);
    }

    /**
     * 获取盘子中的水果
     */
    public List<T> get() {
        return fruitList;
    }
}
