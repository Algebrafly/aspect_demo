package com.algebra.generic;

import com.algebra.generic.entity.Dog;
import com.algebra.generic.entity.Rabbit;

/**
 * @author al
 * @date 2019/8/20 9:39
 * @description class<?> 与 class<T>
 */
public class GenericTest2 {

    private Class<?> clazz;

//    private Class<T> tClass;

    public static <T> T createInstances(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }

    public static void main(String[] args) {

        try {
            Dog a = createInstances(Dog.class);
            Rabbit b = createInstances(Rabbit.class);
            a.printMsg();
            b.printMsg();

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }


    }


}
