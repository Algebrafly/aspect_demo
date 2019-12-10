package com.algebra.aspect.drools.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2019/12/10 13:28
 * @description
 */
@Data
public class RuleText implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String ruleKey;
    private String content;
    private String version;
    private String lastModifyTime;
    private String createTime;
}
