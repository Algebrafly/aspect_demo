package com.algebra.aspect.util.conf;

import com.algebra.aspect.annotation.AccessLimit;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author al
 * @date 2019/12/6 9:13
 * @description
 */
@Component
public class LimitRefreshInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            // 不加注解的直接放行
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean login = accessLimit.needLogin();
            String key = request.getRequestURI();
            //如果需要登录
            if(login){
                //获取登录的session进行判断
                //.....
                key+=""+"1";  //这里假设用户是1,项目中是动态获取的userId
            }

            // 受限的redis 缓存key ,因为这里用浏览器做测试，用sessionid 来做唯一key,如果是app ,可以使用 用户ID 之类的唯一标识。
            String limitKey = request.getServletPath()+request.getSession().getId();
            // 从缓存中获取，当前这个请求访问了几次
            Integer count = (Integer) redisUtil.get(limitKey);
            if(count == null){
                //第一次访问
                redisUtil.set(limitKey,1,seconds);
            }else if(count < maxCount){
                //加1
                redisUtil.incr(limitKey,1);
            }else{
                //超出访问次数
                render(response,"访问受限！请稍后再试！");
                return false;
            }

        }
        return true;
    }

    private void render(HttpServletResponse response, String cm)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str  = JSON.toJSONString(cm);
        out.write(str.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
