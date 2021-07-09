package com.primary.example.jvm;

/**
 * com.primary.example.jvm.GcArgsTest
 *
 * -Xms：堆初始化大小
 * -Xmx：堆最大大小
 * -Xmn：设置新生代大小
 * -XX:+PrintGCDetails：打印gc详细信息
 * -XX:NewRatio=ratio：老年代与新生代占比
 * -XX:SurvivorRatio=ratio：eden、S0、S1占比
 * -XX:TargetSurvivorRatio=6：ygc后新生代期望大小
 * -XX:MaxTenuringThreshold=3：对象晋升老年代阀值
 * -XX:+PrintFlagsInitial：打印jvm参数初始值
 * -XX:+PrintFlagsFinal：打印jvm参数最终值(经过设置之后的值)
 *
 * @author lipeng
 * @date 2021/7/9 2:07 PM
 */
public class GcArgsTest {

}
