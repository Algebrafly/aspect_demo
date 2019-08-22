package com.algebra.aspect.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author al
 * @date 2019/8/22 16:49
 * @description
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonDto {

    private String name;
    /**
     * 对应 Person.user.age
     */
    private Integer age;

    private String email;
    /**
     * 与 DO 里面的字段名称(birthday)不一致
     */
    private Date birth;
    /**
     * 对 DO 里面的字段(birthDay)进行拓展,dateFormat 的形式
     */
    private String birthDateFormat;
    /**
     * 对 DO 里面的字段(birthDay)进行拓展,expression 的形式
     */
    private String birthExpressionFormat;

}
