package com.algebra.aspect.service;

import com.algebra.aspect.domain.JobAndTrigger;
import com.algebra.aspect.util.RequestPageUtil;
import com.github.pagehelper.PageInfo;

/**
 * @author al
 * @date 2020/1/7 14:04
 * @description
 */
public interface IQuartzService {
    /**
     * 定时任务列表查询
     * @param pageUtil 分页参数
     * @return pageInfo
     */
    PageInfo<JobAndTrigger> getJobList(RequestPageUtil pageUtil);
}
