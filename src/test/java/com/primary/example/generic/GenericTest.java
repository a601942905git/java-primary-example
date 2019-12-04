package com.primary.example.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * com.primary.example.generic.GenericTest
 * @link {https://juejin.im/post/5b614848e51d45355d51f792#heading-12}
 * @author lipeng
 * @date 2019-04-24 16:26
 */
public class GenericTest {

    @Test
    public void test() {
        List<String> stringArrayList = new ArrayList<>();
        List<Integer> integerArrayList = new ArrayList<>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试 类型相同");
        }
    }

    @Test
    public void testGeneric() {
        // 装苹果的盘子
        Plate<Apple> applePlate = new Plate<>();
        applePlate.put(new Apple(10001, "苹果"));
        System.out.println("苹果盘子中的苹果个数======>" + applePlate.get().size());
        Apple apple = applePlate.get().get(0);
        System.out.println("从苹果盘子中取出一个苹果======>" + apple);

        System.out.println("====================================");

        // 装香蕉的盘子
        Plate<Banana> bananaPlate = new Plate<>();
        bananaPlate.put(new Banana(10001, "海南香蕉"));
        bananaPlate.put(new Banana(10002, "本地香蕉"));
        System.out.println("香蕉盘子中的香蕉个数======>" + bananaPlate.get().size());
        Banana banana = bananaPlate.get().get(0);
        System.out.println("从香蕉盘子中取出一个香蕉======>" + banana);
    }

    /**
     * 上界通配符extend只能取，不能存
     * 下界通配符super可以存，可以取，但是取出来的类型为object
     * 通配符?适用于不知道类型的场景或者只需要进行Object操作的类型
     */
    @Test
    public void testCommonGeneric() {
        // 编译错误，编译器只认为苹果是水果，但并不认为装苹果的盘子是装水果的盘子
        // Plate<Fruit> plate = new Plate<Apple>(new Apple());
        Plate<? extends Fruit> extendPlate = new Plate<>(new Apple(1000, "apple"));
        //extendPlate.put(new Apple());
        List<Apple> appleList = (List<Apple>) extendPlate.get();
        System.out.println(appleList);

        Plate<? super Fruit> superPlate = new Plate<>(new Apple());
        superPlate.put(new Apple());
        superPlate.put(new Banana());
        superPlate.put(new Fruit());
        Object o = superPlate.get();
        // Apple apple = superPlate.get();
    }
}
