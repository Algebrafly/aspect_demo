package com.algebra.aspect.quartz.spring;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author al
 * @date 2019/9/9 13:12
 * @description
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class TestJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info(new Date() + "任务开始------------------------------------");



    }
}
