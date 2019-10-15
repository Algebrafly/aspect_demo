package com.algebra.aspect.domain.codehelper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author al
 * @date 2019/10/14 17:37
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    private String personId;

    private String personName;

    private Integer personAge;

    private Date personBirthday;

    private String remark;

}
