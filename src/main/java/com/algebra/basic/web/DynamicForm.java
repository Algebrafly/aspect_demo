package com.algebra.basic.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class DynamicForm implements Serializable {

    private Long id;

    private String clientId;

    private String title;

    private String model;

    private String modelGroup;

    private Boolean isComponent;

    private String componentName;

    private Integer modelOrder;

    private String hideStatusIDs;

    private Short componentType;

    private Integer currentStatusID;

    private Boolean isDetail;
}
