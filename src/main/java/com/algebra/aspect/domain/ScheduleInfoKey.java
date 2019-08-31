package com.algebra.aspect.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScheduleInfoKey implements Serializable {
    private String jobName;

    private String triggerName;

}