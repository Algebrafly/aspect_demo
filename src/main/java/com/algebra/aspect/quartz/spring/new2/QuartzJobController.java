package com.algebra.aspect.quartz.spring.new2;

import com.algebra.aspect.quartz.spring.UploadTask;
import com.algebra.aspect.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author al
 * @date 2019/8/23 16:47
 * @description
 */
@Slf4j
@RequestMapping("/quartz")
@RestController()
@Api("Quartz | job增删改查")
public class QuartzJobController {


    @Autowired
    private Scheduler scheduler;

    /**
     * 删除job
     * @param quartzJobDto 定时任务实体参数
     */
    @PostMapping("/deleteJob")
    @ApiOperation("deleteJob")
    public WebApiResult<String> deleteJob(@RequestBody QuartzJobDto quartzJobDto) {
        WebApiResult<String> result = new WebApiResult<>();
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzJobDto.getTriggerName(), quartzJobDto.getTriggerGroupName());
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            JobKey jobKey = JobKey.jobKey(quartzJobDto.getJobName(), quartzJobDto.getJobGroupName());
            scheduler.deleteJob(jobKey);
            result.setSuccess(true);
            result.setMessage("successful");
        } catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false);
            result.setMessage("删除定时任务失败！");

        }
        return result;
    }

    /**
     * 修改定时任务
     * @param quartzJobDto 定时任务实体参数
     */
    @PostMapping("/updateJob")
    @ApiOperation("updateJob")
    public WebApiResult<String> updateJob(@RequestBody QuartzJobDto quartzJobDto) {

        WebApiResult<String> result = new WebApiResult<>();

        try {
            TriggerKey oldTriggerKey = TriggerKey.triggerKey(quartzJobDto.getTriggerName(), quartzJobDto.getTriggerGroupName());
            log.info("要修改的触发器信息：{}, 新cron表达式:{}",oldTriggerKey.toString(), quartzJobDto.getCron());

            // cron 表达式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJobDto.getCron());

            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(oldTriggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.rescheduleJob(oldTriggerKey, cronTrigger);
            result.setSuccess(true);
            result.setMessage("successful");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false);
            result.setMessage("更新定时任务失败！");
        }
        return result;
    }

    /**
     * 新增job任务
     * @param quartzJobDto 定时任务实体参数
     */
    @PostMapping("/addJob")
    @ApiOperation("addJob")
    public WebApiResult<String> addJob(@RequestBody QuartzJobDto quartzJobDto) {

        WebApiResult<String> result = new WebApiResult<>();

        try {
            // 构建cron表达式
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJobDto.getCron());

            // 构建jobDetail：需要具体执行器的类名
            JobDetail jobDetail = JobBuilder.newJob(UploadTask.class)
                    .withIdentity(quartzJobDto.getJobName(), quartzJobDto.getJobGroupName())
                    .build();

            // 构建触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzJobDto.getTriggerName(), quartzJobDto.getTriggerGroupName())
                    .withSchedule(cronScheduleBuilder)
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            result.setSuccess(true);
            result.setMessage("successful");
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false);
            result.setMessage("新增定时任务失败！");
        }
        return result;
    }


    @GetMapping("/getJobList")
    @ApiOperation("getJobList")
    public WebApiResult<QuartzJobDto> getQuartzJobList(){
        WebApiResult<QuartzJobDto> result = new WebApiResult<>();

        return result;
    }


}
