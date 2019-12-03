package com.algebra.basic.stream;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: aspect_demo
 * @author: Algebra
 * @description: stream测试实体类
 * @date: 2019/12/3 20:53
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class StreamTestObject {

    private Long id;

    private String name;

    private int age;

    private boolean gender;

    private String address;

    private Date birthday;

    private BigDecimal salary;

}
