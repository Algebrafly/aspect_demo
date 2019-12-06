package com.algebra.aspect.util.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author al
 * @date 2019/12/6 14:34
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LimitRefreshInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
