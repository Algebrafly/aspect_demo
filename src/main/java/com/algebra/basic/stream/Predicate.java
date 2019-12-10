package com.algebra.basic.stream;

/**
 * @author al
 * @date 2019/12/10 9:21
 * @description 函数式接口：检测指定对象是否符合某些条件
 */
@FunctionalInterface
public interface Predicate {
    /**
     * 接受一个对象，返回是否符合条件
     * @param object 对象
     * @return true-符合条件
     */
    boolean check(StreamTestObject object);
}
