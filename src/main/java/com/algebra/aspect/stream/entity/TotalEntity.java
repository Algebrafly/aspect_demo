package com.algebra.aspect.stream.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author al
 * @date 2019/6/15 11:32
 * @description
 */
@Data
public class TotalEntity implements Serializable {

    private School school;

    private List<MyClass> classList;

    private List<Student> studentList;

    private List<Teacher> teacherList;

    private List<TeacherAndClass> tcList;

}
