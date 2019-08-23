package com.algebra.aspect.quartz.base;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @author al
 * @date 2019/8/23 13:31
 * @description
 */
@Slf4j
public class HelloQuartz implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        JobDetail detail = jobExecutionContext.getJobDetail();
        String name = detail.getJobDataMap().getString("name");
        log.info("my job name is  " + name + " at " + new Date());
    }
}
