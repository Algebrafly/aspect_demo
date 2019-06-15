package com.algebra.aspect.stream.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2019/6/15 11:11
 * @description
 */
@Data
public class MyClass implements Serializable {

    private String clName;

    private String clCode;

    /**
     * 班级所在学校code
     */
    private String scCode;

    public MyClass() {
    }

    public MyClass(String clName, String clCode, String scCode) {
        this.clName = clName;
        this.clCode = clCode;
        this.scCode = scCode;
    }
}
