## List
> 顺序存储，允许元素重复

### ArrayList
> 适用于需要随机访问元素的场景，不适用于新增、删除元素的场景，因为在顺序表中添加和删除元素，需要对顺序表中的元素进行移动，当然在表尾添加和删除元素除外

原理：通过数组的方式来实现，在添加元素的时候，首先判断是否超过默认容量，如果超过，那么将容量扩大为原来的1.5倍

add()源码分析
```java
public boolean add(E e) {
    // 判断是否需要扩容
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    // 将新增的元素放入数组中
    elementData[size++] = e;
    return true;
}
```

### LinkedList
> 双向链表，适用于新增、删除元素的场景，不适用需要随机访问元素的场景，当然访问表头和表位除外。LinkedList提供了诸多对头结点和尾结点进行操作的元素

原理：通过Node结点来实现，每个节点都包含上一个结点对象、结点数据和下一个结点对象

add()源码分析，首先LinkedList定义了头尾结点，用来记录首尾节点的位置
```java
// 头结点
transient Node<E> first;

// 尾结点
transient Node<E> last;
```

```java
public boolean add(E e) {
    // 在尾部新增元素
    linkLast(e);
    return true;
}
```

```java
void linkLast(E e) {
    // 首先记录下尾节点
    final Node<E> l = last;
    // 为新增元素创建一个节点对象
    final Node<E> newNode = new Node<>(l, e, null);
    // 将新增节点作为尾结点
    last = newNode;
    // 如果是第一次添加节点，那么头结点为新增节点
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```

测试示例代码：
```java
package com.primary.example.collection.list;

import org.junit.Test;

import java.util.LinkedList;

/**
 * com.primary.example.collection.list.LinkedListTest
 * 链表
 * 链表顺序存储，不适合随机存取，适合结点添加和删除
 * 提供特殊的删除头尾节点方法，可以以此来实现栈和队列功能
 *
 * @author lipeng
 * @date 2019-04-16 10:56
 */
public class LinkedListTest {

    /**
     * 取元素
     */
    @Test
    public void getElement() {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        System.out.println("取第一个元素：" + numbers.getFirst());
        System.out.println("取第一个元素：" + numbers.getLast());
    }

    /**
     * 移除链表中的第一个元素
     */
    @Test
    public void removeFirst() {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("移除第一个元素：" + numbers.removeFirst());
        numbers.stream().forEach(System.out::println);
    }

    /**
     * 移除链表中的最后一个元素
     */
    @Test
    public void removeLast() {
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("移除最后一个元素：" + numbers.removeLast());
        numbers.stream().forEach(System.out::println);
    }

    /**
     * 使用链表实现栈
     * 栈的特点是先进后出
     */
    @Test
    public void stackTest() {
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.println("元素入栈顺序为：" + i);
            numbers.add(i);
        }

        for (int i = 0, len = numbers.size(); i < len; i++) {
            System.out.println("出栈元素为：" + numbers.removeLast());
        }
    }

    /**
     * 使用链表实现队列
     * 队列的特点是先进先出
     */
    @Test
    public void queueTest() {
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.println("元素入队顺序为：" + i);
            numbers.add(i);
        }

        for (int i = 0, len = numbers.size(); i < len; i++) {
            System.out.println("出队元素为：" + numbers.removeFirst());
        }
    }
}

```

### 栈
> 具有先进后出的特点

示例代码
```java
public class StackTest {

    /**
     * 栈的特点是先进后出
     *
     * peek()取栈顶元素，但是不删除元素
     * pop()删除并返回栈顶元素
     */
    @Test
    public void stackTest() {
        Stack<Integer> stack = new Stack();
        for (int i = 1; i <= 5; i++) {
            System.out.println("入栈元素顺序为：" + stack.push(i));
        }

        System.out.println("取栈顶元素：" + stack.peek());

        for (int i = 1; i <= 5; i++) {
            System.out.println("出栈元素顺序为：" + stack.pop());
        }

    }
}
```

## Set
> 无序，不元素元素重复

### HashSet
> 无序，存储元素唯一

原理：通过HashMap来实现

示例代码：

> 一定要重写hashCode和equals，因为判断是否重复，就是判断hashCode是否一致，如果一致再判断equals是否一致，如果都一致，那么就认为是重复的
```java
public class Person {

    private Integer id;

    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;
        return name.equals(person.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```

```java
public class HashSetTest {

    @Test
    public void setTest() {
        Set<Person> set = new HashSet<>();
        set.add(new Person(10001, "smile"));
        set.add(new Person(10002, "jack"));
        set.add(new Person(10003, "lucky"));
        set.add(new Person(10004, "lily"));
        set.add(new Person(10005, "alone"));
        set.add(new Person(10002, "jack"));
        set.stream().forEach(System.out::println);
    }
}
```
示例结果：
```java
Person{id=10004, name='lily'}
Person{id=10003, name='lucky'}
Person{id=10005, name='alone'}
Person{id=10001, name='smile'}
Person{id=10002, name='jack'}
```

### LinkedHashSet
> 有序，存储元素唯一

原理：通过LinkedHashMap来实现

示例代码：
```
public class LinkedHashSetTest {

    public static void main(String[] args) {
        LinkedHashSet<Person> personLinkedHashSet = new LinkedHashSet<Person>();
        personLinkedHashSet.add(new Person(10001, "smile"));
        personLinkedHashSet.add(new Person(10002, "jack"));
        personLinkedHashSet.add(new Person(10003, "lucky"));
        personLinkedHashSet.add(new Person(10004, "lily"));
        personLinkedHashSet.add(new Person(10005, "alone"));
        personLinkedHashSet.add(new Person(10002, "jack"));
        personLinkedHashSet.stream().forEach(System.out::println);
    }
}
```

示例结果：
```java
Person{id=10001, name='smile'}
Person{id=10002, name='jack'}
Person{id=10003, name='lucky'}
Person{id=10004, name='lily'}
Person{id=10005, name='alone'}
```

### TreeSet
> 存取无序，元素唯一，可以进行排序

原理：通过TreeMap实现

示例代码：
```java
public class TreeSetTest {

    @Test
    public void treeSetTest() {
        TreeSet<Person> treeSet = new TreeSet<>();
        treeSet.add(new Person(10001, "jack"));
        treeSet.add(new Person(10002, "lucky"));
        treeSet.add(new Person(10003, "bob"));
        treeSet.add(new Person(10004, "zone"));
        treeSet.add(new Person(10005, "smile"));
        treeSet.add(new Person(10001, "alone"));
        treeSet.stream().forEach(System.out::println);
    }
}
```

如果自定义对象没有实现Comparable接口，并且在创建TreeSet的时候没有指定比较器，那么就会出现异常
```java
java.lang.ClassCastException: com.primary.example.collection.set.Person cannot be cast to java.lang.Comparable

	at java.util.TreeMap.compare(TreeMap.java:1294)
	at java.util.TreeMap.put(TreeMap.java:538)
	at java.util.TreeSet.add(TreeSet.java:255)
	at com.primary.example.collection.set.TreeSetTest.treeSetTest(TreeSetTest.java:19)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
```

#### 排序实现方式一
如上Person实现Comparable接口，添加实现
```java
@Override
public int compareTo(Person o) {
    Integer result = this.id - o.id;
    if (result == 0) {
        return this.name.compareTo(o.name);
    }
    return result;
}
```

示例结果：
```java
Person{id=10001, name='alone'}
Person{id=10001, name='jack'}
Person{id=10002, name='lucky'}
Person{id=10003, name='bob'}
Person{id=10004, name='zone'}
Person{id=10005, name='smile'}
```
发现是按照id升序排列，如果id一样的话，那么按照名称进行升序排列

#### 排序实现方式二
在创建TreeSet的时候指定Comparator按名称来进行比较

示例代码：
```java
public class TreeSetTest {

    @Test
    public void treeSetTest() {
        // Comparator.comparing第一个参数是按什么字段进行比较，第二个参数使用什么比较器
        TreeSet<Person> treeSet = new TreeSet<>(Comparator.comparing(Person::getName, String::compareTo));
        treeSet.add(new Person(10001, "jack"));
        treeSet.add(new Person(10002, "lucky"));
        treeSet.add(new Person(10003, "bob"));
        treeSet.add(new Person(10004, "zone"));
        treeSet.add(new Person(10005, "smile"));
        treeSet.add(new Person(10001, "alone"));
        treeSet.stream().forEach(System.out::println);
    }
}
```

示例结果：
```java
Person{id=10001, name='alone'}
Person{id=10003, name='bob'}
Person{id=10001, name='jack'}
Person{id=10002, name='lucky'}
Person{id=10005, name='smile'}
Person{id=10004, name='zone'}
```