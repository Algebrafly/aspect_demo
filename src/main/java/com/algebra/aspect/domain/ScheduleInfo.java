package com.algebra.aspect.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author al
 * @decs 定时任务信息列表
 */
@EqualsAndHashCode(callSuper = true)
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