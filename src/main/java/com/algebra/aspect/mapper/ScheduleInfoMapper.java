package com.algebra.aspect.mapper;

import com.algebra.aspect.domain.ScheduleInfo;
import com.algebra.aspect.domain.ScheduleInfoKey;

public interface ScheduleInfoMapper {
    int deleteByPrimaryKey(ScheduleInfoKey key);

    int insert(ScheduleInfo record);

    int insertSelective(ScheduleInfo record);

    ScheduleInfo selectByPrimaryKey(ScheduleInfoKey key);

    int updateByPrimaryKeySelective(ScheduleInfo record);

    int updateByPrimaryKey(ScheduleInfo record);
}