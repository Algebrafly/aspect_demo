package com.algebra.aspect.quartz.spring;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author al
 * @date 2019/8/23 15:15
 * @description
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class UploadTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info(new Date() + "任务开始------------------------------------");
        try {
            JobDetail jobDetail = context.getJobDetail();
            JobKey key = jobDetail.getKey();
            log.info("当前任务名称：{}, 任务所在组:{}",key.getName(),key.getGroup());

            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(new Date() + "任务结束------------------------------------");
    }
}
