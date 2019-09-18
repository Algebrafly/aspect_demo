package com.algebra.aspect.service;

import com.algebra.aspect.domain.ScheduleInfo;

/**
 * @author al
 * @date 2019/9/16 16:59
 * @description 定时任务信息列表
 */
public interface IScheduleInfoService {


    /**
     * 记录定时任务信息
     * @param scheduleInfo record
     */
    void insertScheduleInfo(ScheduleInfo scheduleInfo);

}
