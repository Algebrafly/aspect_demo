package com.algebra.aspect.stragy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author al
 * @date 2019/11/15 17:30
 * @description
 */
@Component
public class QryBProcessor implements QryProcessor {
    @Override
    public boolean check(Map<String, String> request, Map<String, String> result) {
        if(StringUtils.equals(request.get("type"),"b") || Objects.isNull(request.get("type"))){
            return true;
        }
        return false;
    }

    @Override
    public void handle(Map<String, String> request, Map<String, String> result) {
        result.put("B","item_B");
    }
}
