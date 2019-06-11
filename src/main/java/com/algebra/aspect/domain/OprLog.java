package com.algebra.aspect.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OprLog implements Serializable {
    private Integer id;

    private String oprClassName;

    private String oprMethordName;

    private String message;

    private String logTyp;

    private Date oprTime;

}