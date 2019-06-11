package com.algebra.aspect.service.impl;

import com.algebra.aspect.annotation.DoSomething;
import com.algebra.aspect.domain.User;
import com.algebra.aspect.mapper.UserMapper;
import com.algebra.aspect.service.ICacheService;
import com.algebra.aspect.service.ILogService;
import com.algebra.aspect.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author al
 * @date 2019/6/10 19:16
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {

//    @Autowired
//    ILogService logService;
//
//    @Autowired
//    ICacheService<User> cacheService;

    @Autowired
    UserMapper userMapper;

    @Override
    @DoSomething(key = "#id",cacheName = "user")
    @Cacheable(key = "#id",value = "user")
    public User getUserInfoOne(String id, Map<String, Object> param) {

//        User cacherUser = cacheService.cacheGet((String) param.get("uId"),"user");
//        if(cacherUser != null){
//            return cacherUser;
//        }
//        User ret = null;
//        try{
//            ret = userMapper.selectUserOne(param);
//        }catch(Exception e){
//            logService.errorLog(this.getClass().getName(),"getUserInfoOne",e.getMessage(),new Date());
//        }
//        logService.errorLog(this.getClass().getName(),"getUserInfoOne","successful",new Date());
//        if(ret != null){
//            cacheService.cachePut(ret.getUId(),"user",ret);
//        }
//
//        return ret;
        return userMapper.selectUserOne(param);
    }
}
