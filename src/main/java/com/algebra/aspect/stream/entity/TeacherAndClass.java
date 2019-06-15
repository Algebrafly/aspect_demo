package com.algebra.aspect.stream.entity;

import lombok.Data;

/**
 * @author al
 * @date 2019/6/15 11:19
 * @description 老师和班级关联表
 */
@Data
public class TeacherAndClass {

    private String clCode;

    private String teCode;

    public TeacherAndClass(String clCode, String teCode) {
        this.clCode = clCode;
        this.teCode = teCode;
    }
}
