package com.algebra.generic.entity;

import lombok.Data;

/**
 * @author al
 * @date 2019/8/20 10:57
 * @description
 */
@Data
public class Chevrolet extends Car {

    public String carName;

    @Override
    public String getName() {
        return "Chevrolet";
    }
}
