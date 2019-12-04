package com.primary.example.bitmap;

/**
 * com.primary.example.bitmap.BitMap
 *
 * @author lipeng
 * @date 2019-02-02 10:03
 */
public class BitMap {

    /**
     * 存储数据的结构
     */
    private byte[] bytes;

    /**
     * 能保存多少数据
     */
    private int capacity;

    /**
     * 初始化容量
     * 一个字节8位，可以存储8个数据
     * 如果要存储100个数据，那么100 / 8
     * 进行 + 1的原因是除不尽的时候，需要多出一个字节保存多余的数据
     *
     * @param capacity 要存储的数据大小
     */
    public BitMap(int capacity) {
        this.capacity = capacity;
        bytes = new byte[(capacity >> 3) + 1];
    }

    /**
     * 首先找到在那个字节数组
     * 然后在具体的字节中找到相应的位置
     * Index(N) = N / 8
     * Position(N) = N % 8 = N & (8 -1) = N & 0x07
     * 0x07在ASCII Table中对应的就是7
     *
     * {@link http://lwp.interglacial.com/appf_01.htm}
     */
    public void add(int num) {
        int index = num >> 3;
        int position = num & 0x07;
        bytes[index] |= 1 << position;
    }

    public boolean contain(int num) {
        int index = num >> 3;
        int position = num & 0x07;
        return (bytes[index] &= 1 << position) != 0;
    }

    public void clear(int num) {
        int index = num >> 3;
        int position = num & 0x07;
        bytes[index] &= ~(1 << position);
    }
}
