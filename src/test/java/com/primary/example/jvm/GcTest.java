package com.primary.example.jvm;

/**
 * com.primary.example.jvm.GcTest
 *
 * vm options设置：
 * -Xmx200M -Xmn50M -XX:TargetSurvivorRatio=60 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:MaxTenuringThreshold=3
 *
 * 参数说明：
 * -Xmx200M：最大堆内存200M
 * -Xmn50m：新生代内存50M,由于SurvivorRatio默认值为8，可以得出eden：40M，S0、S1：5M
 * -XX:MaxTenuringThreshold=3：最大任期阀值，当对象的age超过该值需要放入老年代
 * -XX:TargetSurvivorRatio=60：ygc后S区期望占用大小(S区中所有age对象加起来超过该值，从age和MaxTenuringThreshold两者最小的为准)
 *
 * @author lipeng
 * @date 2021/7/7 11:41 AM
 */
public class GcTest {


    /**
     * 第1次gc
     * 2021-07-07T15:34:08.069-0800: [GC (Allocation Failure) 2021-07-07T15:34:08.069-0800: [ParNew: 39937K->2725K(46080K), 0.0023161 secs] 39937K->2725K(199680K), 0.0023684 secs] [Times: user=0.01 sys=0.01, real=0.01 secs]
     * 第2次gc
     * 2021-07-07T15:34:11.079-0800: [GC (Allocation Failure) 2021-07-07T15:34:11.079-0800: [ParNew: 43251K->2846K(46080K), 0.0023909 secs] 43251K->2846K(199680K), 0.0024641 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
     * 第3次gc
     * 2021-07-07T15:34:14.086-0800: [GC (Allocation Failure) 2021-07-07T15:34:14.086-0800: [ParNew: 42958K->2699K(46080K), 0.0008913 secs] 42958K->2699K(199680K), 0.0009342 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * 第4次gc，对象年龄为3，超过-XX:MaxTenuringThreshold=3，会放入老年代中
     * 2021-07-07T15:34:17.092-0800: [GC (Allocation Failure) 2021-07-07T15:34:17.092-0800: [ParNew: 43427K->0K(46080K), 0.0049081 secs] 43427K->2632K(199680K), 0.0049487 secs] [Times: user=0.01 sys=0.01, real=0.00 secs]
     * 第5次gc
     * 2021-07-07T15:34:20.101-0800: [GC (Allocation Failure) 2021-07-07T15:34:20.101-0800: [ParNew: 40732K->3072K(46080K), 0.0011457 secs] 43364K->5704K(199680K), 0.0011875 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
     * 第6次gc
     * 2021-07-07T15:34:23.107-0800: [GC (Allocation Failure) 2021-07-07T15:34:23.107-0800: [ParNew: 43806K->0K(46080K), 0.0029244 secs] 46438K->5704K(199680K), 0.0029700 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
     * GC end!
     * Heap
     *  par new generation   total 46080K, used 19027K [0x00000007b3800000, 0x00000007b6a00000, 0x00000007b6a00000)
     *   eden space 40960K,  46% used [0x00000007b3800000, 0x00000007b4a94f08, 0x00000007b6000000)
     *   from space 5120K,   0% used [0x00000007b6000000, 0x00000007b6000000, 0x00000007b6500000)
     *   to   space 5120K,   0% used [0x00000007b6500000, 0x00000007b6500000, 0x00000007b6a00000)
     *  concurrent mark-sweep generation total 153600K, used 5704K [0x00000007b6a00000, 0x00000007c0000000, 0x00000007c0000000)
     *  Metaspace       used 3180K, capacity 4556K, committed 4864K, reserved 1056768K
     *   class space    used 339K, capacity 392K, committed 512K, reserved 1048576K
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        // main方法作为主线程，变量不会被回收
        byte[] byte1 = new byte[1024 * 1024];
        byte[] byte2 = new byte[1024 * 1024];

        // 往eden中填充对象，触发ygc
        YGC(40);
        Thread.sleep(3000);

        // 往eden中填充对象，触发ygc
        YGC(40);
        Thread.sleep(3000);

        // 往eden中填充对象，触发ygc
        YGC(40);
        Thread.sleep(3000);

        // 这次再ygc时, 由于byte1和byte2的年龄经过3次ygc后已经达到3(-XX:MaxTenuringThreshold=3), 所以会晋升到old
        YGC(40);
        // ygc后, s0(from)/s1(to)的空间为0
        Thread.sleep(3000);


        // 达到TargetSurvivorRatio这个比例指定的值,即 5M(S区)*60%(TargetSurvivorRatio)=3M(Desired survivor size)
        byte[] byte4 = new byte[1024 * 1024];
        byte[] byte5 = new byte[1024 * 1024];
        byte[] byte6 = new byte[1024 * 1024];

        // 这次ygc时, 由于s区已经占用达到了60%(-XX:TargetSurvivorRatio=60),
        // 所以会重新计算对象晋升的min(age, MaxTenuringThreshold) = 1
        YGC(40);
        Thread.sleep(3000);

        // 由于前一次ygc时算出age=1, 所以这一次再ygc时, byte4, byte5, byte6就要晋升到Old,
        // 而不需要等MaxTenuringThreshold这么多次, 此次ygc后, s0(from)/s1(to)的空间再次为0, 对象全部晋升到old
        YGC(40);
        Thread.sleep(3000);

        System.out.println("GC end!");
    }

    //塞满Eden区，局部变量会被回收,作为触发GC的小工具
    private static void YGC(int edenSize){
        for (int i = 0 ; i < edenSize ; i ++) {
            byte[] byte1m = new byte[1 * 1024 * 1024];
        }
    }
}
