package com.algebra.aspect.stragy;

import java.util.Map;

/**
 * @author al
 * @date 2019/11/15 17:28
 * @description
 */
public interface QryProcessor {

    boolean check(Map<String,String> request, Map<String,String> result);

    void handle(Map<String,String> request, Map<String,String> result);

}
