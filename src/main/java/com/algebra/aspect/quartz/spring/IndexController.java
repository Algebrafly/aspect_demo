package com.algebra.aspect.quartz.spring;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2019/8/23 15:16
 * @description
 */
@Slf4j
@RequestMapping("/quartz")
@RestController()
public class IndexController {

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() throws SchedulerException {
        //cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/8 * * * * ?");
        //根据name 和group获取当前trgger 的身份
        TriggerKey triggerKey = TriggerKey.triggerKey("cj", "123");
        CronTrigger triggerOld = null;
        try {
            //获取 触发器的信息
            triggerOld = (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if (triggerOld == null) {
            //将job加入到jobDetail中
            JobDetail jobDetail = JobBuilder.newJob(UploadTask.class).withIdentity("cj", "123").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("cj","123").withSchedule(cronScheduleBuilder).build();
            //执行任务
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            log.info("当前job已存在--------------------------------------------");
        }
    }

}
