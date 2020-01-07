package com.algebra.aspect.service.impl;

import com.algebra.aspect.mapper.QuartzJobMapper;
import com.algebra.aspect.domain.JobAndTrigger;
import com.algebra.aspect.service.IQuartzService;
import com.algebra.aspect.util.RequestPageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author al
 * @date 2020/1/7 14:06
 * @description
 */
@Service
public class QuartzServiceImpl implements IQuartzService {

    @Autowired
    QuartzJobMapper quartzJobMapper;

    @Override
    public PageInfo<JobAndTrigger> getJobList(RequestPageUtil pageUtil) {
        if(pageUtil.getExtraParam() == null){
            pageUtil.setExtraParam(new HashMap<>());
        }
        int page = pageUtil.getCurrentPage();
        int pageSize = pageUtil.getPageSize();
        PageHelper.startPage(page,pageSize);
        List<JobAndTrigger> jobList = quartzJobMapper.getJobList(pageUtil.getExtraParam());
        return new PageInfo<>(jobList);
    }
}
