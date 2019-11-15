package com.algebra.aspect.stragy;

import java.util.Map;

/**
 * @author al
 * @date 2019/11/15 17:28
 * @description 与数据库交互/底层业务执行逻辑接扣
 */
public interface QryProcessor {

    /**
     * 服务限定方法
     * @param request 请求参数
     * @param result 结果集
     * @return boolean:true-服务可用
     */
    boolean check(Map<String,String> request, Map<String,String> result);

    /**
     * 服务具体执行逻辑
     * @param request 请求参数
     * @param result 结果集
     */
    void handle(Map<String,String> request, Map<String,String> result);

}
