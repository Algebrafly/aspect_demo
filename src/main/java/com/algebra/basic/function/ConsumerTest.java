package com.algebra.basic.function;

import com.algebra.basic.domain.GirlFriend;
import com.algebra.generic.entity.ext.Person;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author al
 * @date 2020/1/19 10:49
 * @description
 */
public class ConsumerTest {

    public void baseTest(){

        // 1.使用consumer接口实现方法
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        // 2.使用lambda表达式
        Consumer<String> consumer2 = s -> System.out.println(s);

        // 3.使用方法引用
        Consumer<String> consumer3 = System.out::println;

        Stream<String> stream = Stream.of("aaa","ccc","eee","sss");
//        stream.forEach(consumer);
        System.out.println("-----------------------------");

        // 更简洁的方式
        stream.forEach(System.out::println);

    }

    public <P1, T> void test2(Consumer1<T, P1> consumer, P1 p1){
        Consumer<T> c = s -> {
            consumer.deal(s,p1);

            System.out.println(p1);
            System.out.println(consumer.toString());
        };
    }

    @FunctionalInterface
    public interface Consumer1<T, P1>{
        void deal(T t, P1 p1);
    }


    public static void main(String[] args) {

        Consumer1<Person,String> c = new Consumer1<Person, String>() {
            @Override
            public void deal(Person person, String s) {
                person.setName(s);
            }
        };
        // or
        Consumer1<Person,String> c2 = (person, s) -> person.setName(s);
        // or
        Consumer1<Person,String> c3 = Person::setName;

        ConsumerTest test = new ConsumerTest();
        test.test2(c3,"明兰");
        System.out.println("ok");


    }


}
