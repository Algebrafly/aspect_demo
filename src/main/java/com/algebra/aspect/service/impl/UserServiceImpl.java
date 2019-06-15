package com.algebra.aspect.service.impl;

import com.algebra.aspect.annotation.DoSomething;
import com.algebra.aspect.domain.User;
import com.algebra.aspect.mapper.UserMapper;
import com.algebra.aspect.service.ICacheService;
import com.algebra.aspect.service.ILogService;
import com.algebra.aspect.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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


    /*
     * @Cacheable : Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，
     *              而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
     * @CacheEvict: 清除缓存。
     * @CachePut : 可以声明一个方法支持缓存功能。使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前
     * value 指的是 ehcache.xml 中的缓存策略空间；key 指的是缓存的标识，同时可以用 # 来引用参数。
     */

    private static final String CACHE_KEY = "'user'";
    private static final String DEMO_CACHE_NAME = "users";

    @Override
    @CacheEvict(value=DEMO_CACHE_NAME,key=CACHE_KEY)
    public void insertUserInfo(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    @CacheEvict(value = DEMO_CACHE_NAME,key = "'user_'+#uid")//这是清除缓存
    public void deleteUserInfo(String uid) {
        userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    @Cacheable(value=DEMO_CACHE_NAME,key="'user_'+#uid")
    public User selectUserInfo(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    @CachePut(value = DEMO_CACHE_NAME,key = "'user_'+#user.uId()")
    public void updateUserInfo(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
