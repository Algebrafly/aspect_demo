package com.algebra.basic.function;

import com.algebra.basic.domain.GirlFriend;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/1/19 17:21
 * @description
 */
public class SupplierTest {

    public void baseTest(){
        // 使用Supplier接口实现方法,只有一个get方法，无参数，返回一个值
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };

        // 2.lambda表达式
        Supplier<Integer> supplier2 = () -> new Random().nextInt();

        // 3.方法引用
        Supplier<Double> supplier3 =  Math::random;

        System.out.println(supplier3.get());

    }

    public void test2(){
        Stream<Integer> stream = Stream.of(1,2,3,4,5);

        // 返回一个optional对象
        Optional<Integer> first = stream.filter(i -> i > 4).findFirst();

        //optional对象有需要Supplier接口的方法
        //orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
        System.out.println(first.orElse(1));
        System.out.println(first.orElse(7));

        System.out.println("---------------------------");

        Supplier<Integer> supplier = () -> {
            //返回一个随机值
            return new Random().nextInt();
        };

        //orElseGet，如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
        System.out.println(first.orElseGet(supplier));


    }

    public void test3() {

        // 1.简单模式
        Supplier<GirlFriend> supplier = new Supplier<GirlFriend>() {
            @Override
            public GirlFriend get() {
                return new GirlFriend();
            }
        };

        // 2.lambda表达式
        Supplier<GirlFriend> supplier2 = () -> new GirlFriend();

        // 3.引用
        Supplier<GirlFriend> supplier3 = GirlFriend::new;

    }



    public static void main(String[] args) {

        SupplierTest test = new SupplierTest();
        test.test2();


    }

}
