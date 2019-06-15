package com.algebra.aspect.stream;

import com.algebra.aspect.stream.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author al
 * @date 2019/6/15 11:08
 * @description jdk8-stream测试
 */
public class StreamTest {

    private static TotalEntity totalEntity = new TotalEntity();

    private List<School> schoolList = new ArrayList<>();
    private List<MyClass> classList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<Teacher> teacherList = new ArrayList<>();
    private List<TeacherAndClass> tcList = new ArrayList<>();


    public void instanceData(){
        // 学校
        School s1 = new School("仙侠学校","sc_001","AAAAAA省BBB市区");
        School s2 = new School("LOL学校","sc_002","huhuhu");
        Collections.addAll(schoolList,s1,s2);

        // 班级
        MyClass cl1 = new MyClass("仙剑班","cl_001","");
        MyClass cl2 = new MyClass("武侠班","cl_002","");
        MyClass cl3 = new MyClass("艾欧尼亚班","cl_003","sc_002");
        Collections.addAll(classList,cl1,cl2,cl3);

        // 学生
        Student st1 = new Student("李逍遥","st_001","cl_001");
        Student st2 = new Student("赵灵儿","st_002","cl_001");
        Student st3 = new Student("郭靖","st_003","cl_002");
        Collections.addAll(studentList,st1,st2,st3);

        // 老师
        Teacher t1 = new Teacher("胡歌","te_001","sc_001");
        Teacher t4 = new Teacher("刘亦菲","te_004","sc_001");
        Teacher t2 = new Teacher("金庸","te_002","sc_001");
        Teacher t3 = new Teacher("召唤师","te_003","sc_002");
        Collections.addAll(teacherList,t1,t2,t3,t4);

        // 安排老师到班级里面去
        TeacherAndClass tc1 = new TeacherAndClass("cl_001","te_001");
        TeacherAndClass tc3 = new TeacherAndClass("cl_001","te_004");
        TeacherAndClass tc2 = new TeacherAndClass("cl_002","te_002");
        Collections.addAll(tcList,tc1,tc2,tc3);

        // 开班
        totalEntity.setSchool(s1);
        totalEntity.setClassList(classList);
        totalEntity.setStudentList(studentList);
        totalEntity.setTeacherList(teacherList);
        totalEntity.setTcList(tcList);
    }

    public void collectList(TotalEntity totalEntity){

        School school = totalEntity.getSchool();
        List<MyClass> classList = totalEntity.getClassList();
        List<Student> studentList = totalEntity.getStudentList();
        List<Teacher> teacherList = totalEntity.getTeacherList();
        List<TeacherAndClass> tcList = totalEntity.getTcList();

        System.out.println("before-->>"+classList.toString());

        // 给未归属的班级（集合）安排学校
        classList.parallelStream().filter(myClass -> "".equals(myClass.getScCode())).forEach(myClass -> myClass.setScCode(school.getScCode()));

        System.out.println("after-->>"+classList.toString());

        // 学生们都有哪些老师教
        studentList.parallelStream().filter(student -> !"".equals(student.getClCode())).forEach(student -> {
            StringBuilder sb = new StringBuilder();
            tcList.parallelStream().forEach(tc -> {
                if(tc.getClCode().equals(student.getClCode())){
                    // 筛选出老师
                    List<Teacher> teachers = teacherList.parallelStream().filter(teacher ->
                            tc.getTeCode().equals(teacher.getTeCode())).collect(Collectors.toList());

                    teachers.parallelStream().forEach(teacher -> {
                        sb.append(teacher.getTeName()).append(",");
                    });
                }
            });
            System.out.println("学生:"+student.getStName()+"，由老师:"+sb.toString()+" 带领！");
        });

    }



    public static void main(String[] args) {

        StreamTest test = new StreamTest();
        test.instanceData();
        test.collectList(totalEntity);

    }

}
