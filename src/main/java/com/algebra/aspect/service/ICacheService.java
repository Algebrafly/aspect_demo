package com.algebra.aspect.service;

/**
 * @author al
 * @date 2019/6/10 19:52
 * @description
 */
public interface ICacheService<T> {

    T cacheGet(String key, String cacheName);

    void cachePut(String key, String cacheName, T object);

}
