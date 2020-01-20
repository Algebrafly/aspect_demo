package com.algebra.basic.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author al
 * @date 2020/1/16 15:37
 * @description
 */
public class Builder<T> {

    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();

    public Builder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    /**
     * 将要实例化的对象放入supplier中
     * @param instantiator supplier容器
     * @param <T> 对象泛型
     * @return 构造器
     */
    public static <T> Builder<T> of(Supplier<T> instantiator) {
        return new Builder<>(instantiator);
    }

    /**
     * 将类的属性操作（getter & setter）和属性值传入进行操作
     * @param consumer 操作函数
     * @param p1 属性值-1
     * @param <P1> 属性值泛型
     * @return 构造器
     */
    public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
        // 通过原生consumer函数执行自定义的consumer1函数
        Consumer<T> c = instance -> consumer.accept(instance, p1);
        modifiers.add(c);
        return this;
    }
    public <P1, P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
//        Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
        Consumer<T> c = new Consumer<T>() {
            @Override
            public void accept(T t) {
                consumer.accept(t,p1,p2);
            }
        };
        // 把上面构建的consumer函数放入list容器
        modifiers.add(c);
        return this;
    }
    public <P1, P2, P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
        Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
        modifiers.add(c);
        return this;
    }

    /**
     * 构建携带属性值的对象实例
     * @return 对象实例
     */
    public T build() {
        T value = instantiator.get();
        // forEach可以接受一个Consumer函数
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }
    /**
     * 1 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer1<T, P1> {
        void accept(T t, P1 p1);
    }
    /**
     * 2 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer2<T, P1, P2> {
        void accept(T t, P1 p1, P2 p2);
    }
    /**
     * 3 参数 Consumer
     */
    @FunctionalInterface
    public interface Consumer3<T, P1, P2, P3> {
        void accept(T t, P1 p1, P2 p2, P3 p3);
    }
}
