package com.algebra.aspect.stream.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2019/6/15 11:10
 * @description
 */
@Data
public class School implements Serializable {

    private String scName;

    private String scCode;

    private String scAddress;

    public School(){}

    public School(String scName, String scCode, String scAddress) {
        this.scName = scName;
        this.scCode = scCode;
        this.scAddress = scAddress;
    }
}
