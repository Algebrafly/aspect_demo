package com.algebra.basic.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class DynamicFormDetail implements Serializable {

    private Long id;

    private Long dynamicId;

    private String Label;

    private String field;

    private String type;

    private Boolean isDisabled;

    private Boolean isRequired;

    private String options;

    private String unit;

    private String pattern;

    private String regExp;

    private String placeholder;

    private Integer col;

    private String readonlyStatusIDs;

    private Integer minView;

    private Integer startView;

    private Integer fieldOrder;

    private Boolean isComponent;
}
