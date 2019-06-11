package com.algebra.aspect.mapper;

import com.algebra.aspect.domain.User;

import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(String uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserOne(Map<String,Object> param);
}