package com.algebra.aspect.stream.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2019/6/15 11:13
 * @description
 */
@Data
public class Teacher implements Serializable {

    private String teName;
    private String teCode;
    private String scCode;

    public Teacher() {
    }

    public Teacher(String teName, String teCode, String scCode) {
        this.teName = teName;
        this.teCode = teCode;
        this.scCode = scCode;
    }
}
