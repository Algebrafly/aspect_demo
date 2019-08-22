package com.algebra.aspect.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author al
 * @date 2019/8/22 16:48
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;
    private String name;
    private String email;
    private Date birthday;
    private User user;

}
