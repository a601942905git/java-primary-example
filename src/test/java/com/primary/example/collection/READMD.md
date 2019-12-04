## 为什么不能在foreach中添加或者删除元素？
说什么都不如我们实际操作一下，直接上代码
```
public class ForeachAddAndRemoveElementTest {

    public static final String DELETE_NAME = "smile";

    public static void main(String[] args) {
        List<String> names = new ArrayList(){{
            add("jack");
            add("allen");
            add("smile");
            add("lucy");
            add("zone");
        }};

        for (String name : names) {
            if (Objects.equals(name, DELETE_NAME)) {
                names.remove(name);
            }
        }
    }
}
```
然后愉快的启动运行，期待的打开控制台看看是否会有惊喜
```
Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
	at java.util.ArrayList$Itr.next(ArrayList.java:859)
	at com.primary.example.collection.ForeachAddAndRemoveElementTest.main(ForeachAddAndRemoveElementTest.java:26)
```
控制台果然有惊喜，此时我们用实践证明了如上结论的正确性，那么接下来在分析分析原理，看看为什么会出现这种情况，以加深对foreach的理解

## 原理分析
从如上的异常信息我们可以看到是ArrayList内部类Itr中的checkForComodification方法报错了，那么来看看该方法的实现
```
final void checkForComodification() {
    if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
}
```
从该方法可以看出，抛出异常的原因就是modCount和expectedModCount不相等，那么接下来我们来理理这两个字段是干什么用的。

modCount字段上面的注释如下
```
The number of times this list has been <i>structurally modified</i>
```
也就是说该字段用于记录集合结构被修改的次数

那么再来看看expectedModCount的注释
```
int expectedModCount = modCount;
```
可以看到初始化的时候将modCount赋值给了expectedModCount，也就是说一开始它们是相等的，在某一时刻就不相等了。那么我们来看看remove方法的实现。此时的remove方法是属于List的
```
public boolean remove(Object o) {
    if (o == null) {
        for (int index = 0; index < size; index++)
            if (elementData[index] == null) {
                fastRemove(index);
                return true;
            }
    } else {
        for (int index = 0; index < size; index++)
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
    }
    return false;
}
```
可以看到会调用fastRemove(index);
```
private void fastRemove(int index) {
    modCount++;
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work
}
```
fastRemove方法中只对modCount++进行了修改，记录集合被修改的次数，但是没有修改exceptedModCount，所以此时两个字段的值就不相等。

我们都知道，foreach底层使用的Iterator，遍历完成一次之后会调用next，来看看ArrayList内部类Itr中的next方法
```
public E next() {
    checkForComodification();
    int i = cursor;
    if (i >= size)
        throw new NoSuchElementException();
    Object[] elementData = ArrayList.this.elementData;
    if (i >= elementData.length)
        throw new ConcurrentModificationException();
    cursor = i + 1;
    return (E) elementData[lastRet = i];
}
```
第一行调用了checkForComodification();这个方法我们之前分析过，会判断modCount和expectedModCount是否相等，如果不相等就会抛出异常。上面对fastRemove分析的时候得出结论就是两个字段不相等，所以就会抛出异常。
```
final void checkForComodification() {
    if (modCount != expectedModCount)
        throw new ConcurrentModificationException();
}
```

## 总结
抛出异常的原因就是modCount属于ArrayList，而expectedModCount属于内部类Itr，调用ArrayLit的remove方法只修改modCount，导致两个字段不一样，从而报错。

## 解决方案
1. 使用Iterator，调用Iterator的remove方法
```
public class ForeachAddAndRemoveElementTest {

    public static final String DELETE_NAME = "smile";

    public static void main(String[] args) {
        List<String> names = new ArrayList(){{
            add("jack");
            add("allen");
            add("smile");
            add("lucy");
            add("zone");
        }};

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), DELETE_NAME)) {
                iterator.remove();
            }
        }

        names.stream().forEach(System.out::println);
    }
}
```
如上输出结果
```
jack
allen
lucy
zone
```
可以看到程序正确执行了，那我们来看看ArrayList内部类Itr的remove方法
```
public void remove() {
    if (lastRet < 0)
        throw new IllegalStateException();
    checkForComodification();

    try {
        ArrayList.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
        expectedModCount = modCount;
    } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
    }
}
```
可以看到有一个赋值操作，也就是modCount修改完成之后，会赋值给expectedModCount，保证两个字段相等，所以next中就不会报错
2. 使用正常的for循环
```
public class ForeachAddAndRemoveElementTest {

    public static final String DELETE_NAME = "smile";

    public static void main(String[] args) {
        List<String> names = new ArrayList(){{
            add("jack");
            add("allen");
            add("smile");
            add("lucy");
            add("zone");
        }};

        for (int i = names.size() - 1; i >=0; i--) {
            if (Objects.equals(names.get(i), DELETE_NAME)) {
                names.remove(names.get(i));
            }
        }
        names.stream().forEach(System.out::println);
    }
}
```
采用从后往前的遍历，从后往前不会导致集合的内部结构发生变化，如果是从前往后，会导致在删除元素后，集合的结构发生改变，会出现下标越界异常
3. 使用Stream
```
public class ForeachAddAndRemoveElementTest {

    public static final String DELETE_NAME = "smile";

    public static void main(String[] args) {
        List<String> names = new ArrayList(){{
            add("jack");
            add("allen");
            add("smile");
            add("lucy");
            add("zone");
        }};

        names.stream().filter((name) -> !Objects.equals(name, DELETE_NAME)).forEach(System.out::println);
    }
}
```