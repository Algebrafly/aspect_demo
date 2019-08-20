package com.algebra.generic;

import com.algebra.generic.entity.Animal;
import com.algebra.generic.entity.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2019/8/20 9:16
 * @description 泛型测试类1 : 无界通配符
 */
public class GenericTest {

    static int countLegs (List<? extends Animal> animals){
        int retVal = 0;
        for (Animal animal:animals){
            retVal += animal.getAnimalLegs();
        }
        return retVal;
    }

    static int countLegs1 (List<Animal> animals){
        int retVal = 0;
        for (Animal animal:animals){
            retVal += animal.getAnimalLegs();
        }
        return retVal;
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();

        countLegs(dogs);

//        countLegs1(dogs); // 编译错误



    }

}
