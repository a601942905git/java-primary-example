package com.primary.example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * com.primary.example.jvm.GcTest1
 *
 * @author lipeng
 * @date 2021/7/8 11:31 AM
 */
public class GcTest1 {

    private static byte[] buffer = new byte[1024];

    public static void main(String[] args) throws InterruptedException {
        List<byte[]> stringList = new ArrayList<>();
        while (true) {
            stringList.add(buffer);
        }
    }
}
