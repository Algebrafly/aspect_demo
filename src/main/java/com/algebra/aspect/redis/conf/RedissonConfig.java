package com.algebra.aspect.redis.conf;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author al
 * @date 2020/1/15 17:30
 * @description
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 采用默认编码序列化模式，单节点
        config.setCodec(new JsonJacksonCodec())
                .useSingleServer()
                .setAddress("redis://49.233.146.198:6379");
        return Redisson.create(config);
    }
}
