package com.algebra.generic.entity.ext;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

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

    private String id;
    private Date  birthday;
    private Integer age;
    private BigDecimal price;

    @Override
    public int compareTo(Person o) {
        if(this.age > o.age){
            return 1;
        }
        Comparator<Person> comparator = (Comparator<Person>) (o1, o2) -> {
            // TODO Auto-generated method stub
            Date date1 = o1.getBirthday();
            Date date2 = o2.getBirthday();
            return date1.compareTo(date2);
        };
        return 0;
    }

    public static void main(String[] args) {
        Person p1 = Person.builder().age(18).build();
        Person p2 = Person.builder().age(20).build();

        System.out.println(p1.compareTo(p2));

    }

}
