package com.primary.example.collection.map;

import org.junit.Test;

/**
 * com.primary.example.collection.map.HashTest
 *
 * @author lipeng
 * @date 2019-05-08 11:13
 */
public class HashTest {

    @Test
    public void test() {
        int length = 16;
        int hashCode1 = hash("key1");
        int index1 = indexFor(hashCode1, length);
        System.out.println("index1======>" + index1);

        int hashCode2 = hash("key2");
        int index2 = indexFor(hashCode2, length);
        System.out.println("index2======>" + index2);

        int hashCode3 = hash("fsldf");
        int index3 = indexFor(hashCode3, length);
        System.out.println("index3======>" + index3);

        int hashCode4 = hash("gdfg");
        int index4 = indexFor(hashCode4, length);
        System.out.println("index4======>" + index4);

        int hashCode5 = hash("hghgh");
        int index5 = indexFor(hashCode5, length);
        System.out.println("index5======>" + index5);

        int hashCode6 = hash("sgdgdh");
        int index6 = indexFor(hashCode6, length);
        System.out.println("index6======>" + index6);

        System.out.println("=============resize==============");
        length = 32;

        index1 = indexFor(hashCode1, length);
        System.out.println("index1======>" + index1);

        index2 = indexFor(hashCode2, length);
        System.out.println("index2======>" + index2);

        index3 = indexFor(hashCode3, length);
        System.out.println("index3======>" + index3);

        index4 = indexFor(hashCode4, length);
        System.out.println("index4======>" + index4);

        index5 = indexFor(hashCode5, length);
        System.out.println("index5======>" + index5);

        index6 = indexFor(hashCode6, length);
        System.out.println("index6======>" + index6);
    }

    private int indexFor(int hashCode, int length) {
        return hashCode & (length - 1);
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
