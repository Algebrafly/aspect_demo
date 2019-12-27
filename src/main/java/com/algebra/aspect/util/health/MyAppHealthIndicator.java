package com.algebra.aspect.util.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2019/12/27 16:06
 * @description
 */
@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        //自定义的检查方法
        //Health.down().build()代表健康
        return Health.up().withDetail("msg","服务正常").build();
    }
}
