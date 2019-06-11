package com.algebra.aspect.mapper;

import com.algebra.aspect.domain.OprLog;

public interface OprLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OprLog record);

    int insertSelective(OprLog record);

    OprLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OprLog record);

    int updateByPrimaryKey(OprLog record);
}