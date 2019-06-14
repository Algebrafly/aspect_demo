package com.algebra.aspect.asp;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author al
 * @date 2019/6/11 10:04
 * @description
 */
@Component
@Aspect
@Slf4j
public class WebLogAspect {

    /**
     * 定义切入点：切入点为controller包下的所有函数
     * execution([可见性]返回类型[声明类型].方法名(参数)[异常])
     * 其中[]内的是可选的，其它的还支持通配符的使用：
     * 1) *：匹配所有字符
     * 2) ..：一般用于匹配多个包，多个参数
     * 3) +：表示类及其子类
     * 4)运算符有：&&,||,!
     */
    @Pointcut("execution(public * com.algebra.aspect.controller..*.*(..))")
    public void webLog(){}

    /**
     * 前置通知，方法调用前被调用
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        log.info("doBefore -------------------->> 切入点之前的通知");

        log.info("目标方法参数信息：{}", joinPoint.getArgs());
        log.info("代理类的信息：{}", joinPoint.getThis());
        log.info("代理的目标对象：{}", joinPoint.getTarget());

        // 1.通知签名信息
        Signature signature = joinPoint.getSignature();
        log.info("代理的方法: {}",signature.getName());
        log.info("代理类的名字：{}", signature.getDeclaringTypeName());

        // 2.获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        Map<String, String[]> parameterMap = request.getParameterMap();

        log.info("请求参数信息：{}", parameterMap.toString());
    }

    /**
     * 后置返回通知
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法相应参数类型一致时才能执行后置返回通知，否则不执行，
     *           对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret){

        log.info("RESPONSE:" + ret);
    }


    /**
     * 后置异常通知
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型一致时才能执行后置异常通知，否则不执行，
     *          对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(throwing = "exception",pointcut = "webLog()")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        log.info(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            log.info("发生了空指针异常!!!!!");
        }
    }

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法,不论是正常返回还是异常退出）
     * @param joinPoint
     */
    @After(value = "webLog()")
    public void doAfterAdvice(JoinPoint joinPoint){
        log.info("后置最终通知执行了!!!!");
    }


    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(value = "webLog()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        log.info("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
        try {
            Object obj = proceedingJoinPoint.proceed();
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
