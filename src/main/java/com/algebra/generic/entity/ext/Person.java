package com.algebra.generic.entity.ext;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author al
 * @date 2019/11/21 17:34
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Person implements Serializable, Comparable<Person>{

    private String name;
    private Date  birthday;
    private String birthdayString;
    private Integer age;
    private boolean gender;
    private BigDecimal salary;
    private String salaryString;

    /**
     * person排序工具类，先按照年龄升序，年龄相同按照入职降序，入职日期相同按照薪水升序排序。
     * @param o 排序对象
     */
    @Override
    public int compareTo(Person o) {
        if(!this.age.equals(o.age)){
            return this.age - o.age;
        } else {
            if(!this.birthday.equals(o.birthday)){
                Comparator<Person> comparator = (o1, o2) -> {
                    Date date1 = o1.getBirthday();
                    Date date2 = o2.getBirthday();
                    return - date1.compareTo(date2);
                };
            } else {
                Comparator<Person> comparator = (o1, o2) -> {
                    BigDecimal data1 = o1.getSalary();
                    BigDecimal data2 = o2.getSalary();
                    return data1.compareTo(data2);
                };
            }
        }
        return 0;
    }

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.00");

        Person p1 = Person.builder().name("tom").gender(true).age(19)
                .birthday(sdf.parse("2018-10-10")).birthdayString("2018-10-10")
                .salaryString("2500").salary(new BigDecimal("2500")).build();

        Person p2 = Person.builder().name("jerry").gender(true).age(20)
                .birthday(sdf.parse("2017-9-9")).birthdayString("2017-9-9")
                .salaryString("6000").salary(new BigDecimal(6000)).build();

        Person p3 = Person.builder().name("lily").gender(false).age(20)
                .birthday(sdf.parse("2019-10-11")).birthdayString("2019-10-11")
                .salaryString("3000").salary(new BigDecimal("3000")).build();

        Person p4 = Person.builder().name("张小敬").gender(true).age(20)
                .birthday(sdf.parse("2019-10-11")).birthdayString("2019-10-11")
                .salaryString("5000").salary(new BigDecimal("5000")).build();

        Person p5 = Person.builder().name("檀棋").gender(false).age(24)
                .birthday(sdf.parse("2018-10-10")).birthdayString("2018-10-10")
                .salaryString("8000").salary(new BigDecimal("8000")).build();

        List<Person> al = new ArrayList<>();
        Collections.addAll(al,p1,p2,p3,p4,p5);

        System.out.println("[排序前：---------------------->>>]");
        al.forEach(data -> System.out.println(data.toString()));

        /* 先按照年龄升序，年龄相同按照入职降序，入职日期相同按照薪水升序排序 */
//        al.sort(Person::compareTo);

        /* 按照日期排序（利用自定义的排序规则类） */
//        al.sort(new PersonOrderbyBirthdayUtil());

        /* 按照薪水排序（利用自定义的排序规则类） */
//        al.sort(new PersonOrderbySalaryUtil());

        Collections.sort(al);

        System.out.println("[排序后：---------------------->>>]");
        al.forEach(data -> System.out.println(data.toString()));




    }

}
