package com.algebra.aspect.asp;

import com.algebra.aspect.annotation.DoSomething;
import com.algebra.aspect.domain.User;
import com.algebra.aspect.service.ICacheService;
import com.algebra.aspect.service.ILogService;
import com.algebra.aspect.util.SpelParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author al
 * @date 2019/6/10 20:09
 * @description
 */
@Component
@Aspect
public class DoSomethingAspect {

    @Autowired
    ILogService logService;

    @Autowired
    ICacheService<User> cacheService;

    @Around("@annotation(doSomething)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, DoSomething doSomething) throws Throwable {
        String key = getKey(doSomething.key(),proceedingJoinPoint);
        String cacheName = doSomething.cacheName();
        boolean needLog = doSomething.needLog();

        User cacheUser = cacheService.cacheGet(key,cacheName);
        if(cacheUser != null){
            return cacheUser;
        }
        Object ret = null;
        try{
            ret = proceedingJoinPoint.proceed();
        }catch(Exception e){
            logService.errorLog(this.getClass().getName(),"getUserInfoOne",e.getMessage(),new Date());
        }
        logService.infoLog(this.getClass().getName(),"getUserInfoOne","successful",new Date());
        if(ret != null){
            cacheService.cachePut(key,"user", (User) ret);
        }
        return ret;
    }

    private String getKey(String key, ProceedingJoinPoint proceedingJoinPoint){
        Method method = ((MethodSignature)proceedingJoinPoint.getSignature()).getMethod();
        String[] paramNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        return SpelParser.getKey(key,paramNames,proceedingJoinPoint.getArgs());
    }

}
