package com.algebra.aspect.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScheduleInfo extends ScheduleInfoKey implements Serializable {
    private String jobGroupName;

    private String triggerGroupName;

    private String jobClassName;

    private String cron;

    private String triggerStatus;

    private Date crtTime;

    private Date updTime;

    private String crtId;

    private String updId;

}