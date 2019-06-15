package com.algebra.aspect.stream.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2019/6/15 11:14
 * @description
 */
@Data
public class Student implements Serializable {

    private String stName;
    private String stCode;

    /**
     * 学生所在班级编号
     */
    private String clCode;

    public Student() {
    }

    public Student(String stName, String stCode, String clCode) {
        this.stName = stName;
        this.stCode = stCode;
        this.clCode = clCode;
    }
}
