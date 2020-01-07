package com.algebra.aspect.mapper;

import com.algebra.aspect.domain.JobAndTrigger;

import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2020/1/7 13:39
 * @description
 */
public interface QuartzJobMapper {

    /**
     * 获取Job列表
     * @param param 筛选条件
     * @return list
     */
    List<JobAndTrigger> getJobList(Map<String,Object> param);

}
