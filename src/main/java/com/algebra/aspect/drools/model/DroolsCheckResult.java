package com.algebra.aspect.drools.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2019/12/10 13:30
 * @description
 */
@Data
public class DroolsCheckResult implements Serializable {
    /**
     * true:通过校验；false：未通过校验
     */
    private boolean postCodeResult = false;
}
