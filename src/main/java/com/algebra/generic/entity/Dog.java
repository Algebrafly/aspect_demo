package com.algebra.generic.entity;

import lombok.Data;

/**
 * @author al
 * @date 2019/8/20 9:19
 * @description
 */
@Data
public class Dog extends Animal {

    private String animalName;

    @Override
    public String getName() {
        return "Dog";
    }

}
