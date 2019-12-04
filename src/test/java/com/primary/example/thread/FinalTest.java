package com.primary.example.thread;

/**
 * com.primary.example.thread.FinalTest
 *
 * @author lipeng
 * @date 2019/11/28 下午3:29
 */
public class FinalTest {

    private static final Integer a;

    private static final Integer b = 10;

    private final Integer c;

    private final Integer d;

    private final Integer e = 20;

    static {
        a = 10;
    }

    {
        c = 20;
    }

    public FinalTest() {
        d = 20;
    }

    public void test() {
        final Integer f;
        f = 20;
    }
}
