package com.algebra.aspect.service.impl;

import com.algebra.aspect.service.ICacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author al
 * @date 2019/6/10 19:53
 * @description
 */
@Service
@Slf4j
public class CacheServiceImpl<T> implements ICacheService<T> {


    @Override
    public T cacheGet(String key, String cacheName) {
        log.info("根据key = {} ,获取缓存数据 {}",key, cacheName);
        return null;
    }

    @Override
    public void cachePut(String key,String cacheName, T object) {
        log.info("缓存key = {}  的数据 {}",key, object);

    }
}
