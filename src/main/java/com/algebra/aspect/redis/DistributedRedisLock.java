package com.algebra.aspect.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author al
 * @date 2020/1/15 17:35
 * @description
 */
@Component
public class DistributedRedisLock {
    @Autowired
    private RedissonClient redissonClient;

    private static final String LOCK_TITLE = "redisLock_";
    //加锁
    public boolean acquire(String lockName){
        //声明key对象
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        RLock mylock = redissonClient.getLock(key);
        //加锁，并且设置锁过期时间，防止死锁的产生
        mylock.lock(10, TimeUnit.SECONDS);
        System.err.println("======lock======"+Thread.currentThread().getName());
        //加锁成功
        return  true;
    }
    //锁的释放
    public void release(String lockName){
        //必须是和加锁时的同一个key
        String key = LOCK_TITLE + lockName;
        //获取所对象
        RLock mylock = redissonClient.getLock(key);
        //释放锁（解锁）
        mylock.unlock();
        System.err.println("======unlock======"+Thread.currentThread().getName());
    }
}
