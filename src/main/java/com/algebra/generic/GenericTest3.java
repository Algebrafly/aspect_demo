package com.algebra.generic;

import com.algebra.generic.entity.Animal;
import com.algebra.generic.entity.Dog;
import com.algebra.generic.entity.GlmapperGeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2019/8/20 9:16
 * @description 泛型测试类
 */
public class GenericTest3 {

    public static void main(String[] args) {

        GlmapperGeneric<String> test = new GlmapperGeneric<>();
        test.setT("asdasd");

        String t = test.getT();
        System.out.println(t);


    }

}
