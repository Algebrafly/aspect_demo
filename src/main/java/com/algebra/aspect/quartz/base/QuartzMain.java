package com.algebra.aspect.quartz.base;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author al
 * @date 2019/8/23 13:28
 * @description
 */
public class QuartzMain {

    public static void main(String[] args) {
        JobDetail jobDetail = JobBuilder.newJob(HelloQuartz.class)
                .withIdentity("job","group-01")
                .usingJobData("name","algebra")
                .build();

        /* withSchedule的参数-1：simpleSchedule */
        // 每隔一秒执行一次，永久重复
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever();

        /* withSchedule的参数-2：dailyTimeIntervalSchedule */
        // 每周一和周二的9点开始，到17点结束，每次间隔一小时执行
        DailyTimeIntervalScheduleBuilder dailyTimeIntervalScheduleBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0))
                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(17, 0))
                .onDaysOfTheWeek(1, 2)
                .withIntervalInHours(1);

        /* withSchedule的参数-3：calendarIntervalScheduleBuilder */
        // 一周执行一次
        CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                .withIntervalInWeeks(1);

        /* withSchedule的参数-4：calendarIntervalScheduleBuilder */
        // 每天8:00-17:00，每隔2分钟执行一次
        CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?");


        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger","group-01")
                .startNow()
                .withSchedule(simpleScheduleBuilder)
                .build();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
            Thread.sleep(6000);
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }



    }

}
