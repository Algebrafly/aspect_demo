package com.algebra.aspect.quartz.spring.new2;

import lombok.Data;

/**
 * @author al
 * @date 2019/8/23 17:00
 * @description
 */
@Data
public class QuartzJobDto {

    /**
     * 工作名称
     */
    private String jobName;
    /**
     * 工作组名称
     */
    private String jobGroupName;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组名称
     */
    private String triggerGroupName;
    /**
     *  执行器全类路径名
     */
    private String jobClassName;
    /**
     * cron表达式
     */
    private String cron;

}
