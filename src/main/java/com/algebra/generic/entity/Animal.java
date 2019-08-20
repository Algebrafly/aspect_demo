package com.algebra.generic.entity;

import lombok.Data;

/**
 * @author al
 * @date 2019/8/20 9:17
 * @description
 */
@Data
public abstract class Animal extends BaseEntity{

    private Integer animalLegs;

    public void printMsg(){
        System.out.println("Hello "  + getName());
    }


}
