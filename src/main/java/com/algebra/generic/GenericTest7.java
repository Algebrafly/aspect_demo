package com.algebra.generic;

import com.algebra.generic.entity.ext.*;

/**
 * @author al
 * @date 2019/11/19 9:44
 * @description
 */
public class GenericTest7 {

    public static void main(String[] args) {

        Plate<? extends Fruit> p = new Plate<>(new Apple());

        System.out.println(p.getItem().getName());
//        p.setItem(new Banana());


        Plate<? super Fruit> p2 = new Plate<>();
        p2.setItem(new Apple());
//        p2.setItem(new Meat());
//        p2.setItem(new Food());

        System.out.println(p2.getItem());


    }


}
