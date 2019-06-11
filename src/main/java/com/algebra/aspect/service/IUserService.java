package com.algebra.aspect.service;

import com.algebra.aspect.domain.User;

import java.util.Map;

/**
 * @author al
 * @date 2019/6/10 19:15
 * @description
 */
public interface IUserService {

    User getUserInfoOne(String id, Map<String,Object> param);
}
