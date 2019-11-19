package com.algebra.aspect.quartz.spring.new2;

import com.algebra.aspect.quartz.spring.UploadTask;
import com.algebra.aspect.util.WebApiResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * 查询任务当前状态
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * 状态码：
     *          NONE: 不存在
     * 	  		NORMAL: 正常
     * 	  		PAUSED: 暂停
     * 	  		COMPLETE:完成
     * 	  		ERROR : 错误
     */
    @GetMapping("/getTriggerStatus")
    @ApiOperation("getTriggerStatus")
    public WebApiResult<String> getTriggerStatus(@RequestParam("jobName") String jobName,
                                                 @RequestParam(value = "jobGroupName",required = false) String jobGroupName){
        WebApiResult<String> result = new WebApiResult<>();
        log.info("要查询的任务名称：{}，任务所在组：{}",jobName,jobGroupName);
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            result.setData(triggerState.name());
            result.setMessage("查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询任务状态出现异常");
            result.setSuccess(false);
            result.setMessage("查询任务状态出现异常！");
        }
        return result;
    }

    /**
     * 关闭所有定时调度任务
     */
    @GetMapping("/shutdownAllJobs")
    @ApiOperation("shutdownAllJobs")
    public WebApiResult<String> shutdownAllJobs(){
        WebApiResult<String> result = new WebApiResult<>();
        try {
            if(!scheduler.isShutdown()){
                scheduler.shutdown();
            }
            result.setData("successful");
            result.setMessage("关闭所有定时任务成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("关闭所有定时任务出现异常");
            result.setSuccess(false);
            result.setMessage("关闭所有定时任务出现异常！");
        }
        return result;
    }

    /**
     * 开启所有定时调度任务
     */
    @GetMapping("/startAllJobs")
    @ApiOperation("startAllJobs")
    public WebApiResult<String> startAllJobs(){
        WebApiResult<String> result = new WebApiResult<>();
        try {
            scheduler.start();
            result.setData("successful");
            result.setMessage("开启所有定时任务成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("开启所有定时任务出现异常");
            result.setSuccess(false);
            result.setMessage("开启所有定时任务出现异常！");
        }
        return result;
    }

    /**
     * 恢复一个任务
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     */
    @GetMapping("/resumeJob")
    @ApiOperation("resumeJob")
    public WebApiResult<String> resumeJob(@RequestParam("jobName") String jobName,
                                         @RequestParam(value = "jobGroupName",required = false) String jobGroupName) {
        WebApiResult<String> result = new WebApiResult<>();
        log.info("要恢复的任务名称：{}，任务所在组：{}",jobName,jobGroupName);
        try {
            JobKey jobKey = JobKey.jobKey(jobName,jobGroupName);
            scheduler.resumeJob(jobKey);
            result.setData("successful");
            result.setMessage("恢复成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("恢复任务出现异常");
            result.setSuccess(false);
            result.setMessage("恢复任务出现异常！");
        }
        return result;
    }

    /**
     * 暂停一个任务
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     */
    @GetMapping("/pauseJob")
    @ApiOperation("pauseJob")
    public WebApiResult<String> pauseJob(@RequestParam("jobName") String jobName,
                                         @RequestParam(value = "jobGroupName",required = false) String jobGroupName) {
        WebApiResult<String> result = new WebApiResult<>();
        log.info("要暂停的任务名称：{}，任务所在组：{}",jobName,jobGroupName);
        try {
            JobKey jobKey = JobKey.jobKey(jobName,jobGroupName);
            scheduler.pauseJob(jobKey);
            result.setData("successful");
            result.setMessage("暂停成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("暂停任务出现异常");
            result.setSuccess(false);
            result.setMessage("暂停任务出现异常！");
        }
        return result;
    }

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
            log.info("成功移除任务");
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
        log.info("[添加定时调度任务]-请求参数：{}", quartzJobDto.toString());
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
        log.info("[添加定时调度任务]-请求参数：{}", quartzJobDto.toString());
        try {
            // 构建cron表达式
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJobDto.getCron());

            Class<? extends QuartzJobBean> aClass = (Class<? extends QuartzJobBean>) Class.forName(quartzJobDto.getJobClassName());
            // 构建jobDetail：需要具体执行器的类名
            JobDetail jobDetail = JobBuilder.newJob(aClass)
                    .withIdentity(quartzJobDto.getJobName(), quartzJobDto.getJobGroupName())
                    .build();

            jobDetail.getJobDataMap().put("quartzJobDto",JSONObject.toJSONString(quartzJobDto));
            // 构建触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzJobDto.getTriggerName(), quartzJobDto.getTriggerGroupName())
                    .withSchedule(cronScheduleBuilder)
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            if(!scheduler.isShutdown()){
//                scheduler.start();
                log.info("任务：{}，任务参数：{}，已启动----------->>>>",quartzJobDto.getJobName(),quartzJobDto.getCron());
            }
            result.setSuccess(true);
            result.setMessage("successful");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result.setSuccess(false);
            result.setMessage("新增定时任务失败！");
        }
        return result;
    }


    @GetMapping("/getJobList")
    @ApiOperation("getJobList")
    public WebApiResult<List<QuartzJobDto>> getQuartzJobList(){
        WebApiResult<List<QuartzJobDto>> result = new WebApiResult<>();
        try {
            List<QuartzJobDto> scheduleJobList = new ArrayList<>();
            // 获取所有任务列表
            GroupMatcher<JobKey> matcher = GroupMatcher.anyGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    QuartzJobDto scheduleJob = new QuartzJobDto();
                    scheduleJob.setJobName(jobKey.getName());
                    scheduleJob.setJobGroupName(jobKey.getGroup());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    scheduleJob.setJobStatus(triggerState.name());
                    //获取要执行的定时任务类名
                    JobDetail jobDetail=scheduler.getJobDetail(jobKey);
                    scheduleJob.setJobClassName(jobDetail.getJobClass().getName());

                    if (trigger instanceof CronTrigger) {
                        CronTrigger cron = (CronTrigger) trigger;
                        scheduleJob.setCron(cron.getCronExpression());
                    }
                    scheduleJobList.add(scheduleJob);
                }
            }

            result.setData(scheduleJobList);

        } catch (Exception e){
            e.printStackTrace();
            log.error("查询Quartz任务列表异常");
            result.setSuccess(false);
            result.setMessage("查询Quartz任务列表异常");
        }
        return result;
    }


}
