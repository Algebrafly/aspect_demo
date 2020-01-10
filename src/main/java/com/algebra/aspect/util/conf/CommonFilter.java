package com.algebra.aspect.util.conf;

import com.algebra.basic.thread.RequestManager;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author al
 * @date 2020/1/10 14:10
 * @description
 */
public class CommonFilter extends OncePerRequestFilter {
    /**
     * 拦截所有的http请求,需要配置过滤器
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            RequestManager.setHttpServletRequest(request);
            filterChain.doFilter(request,response);
        } finally {
            RequestManager.removeHttpServletRequest();
        }
    }

}
