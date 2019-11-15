package com.algebra.aspect.stragy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author al
 * @date 2019/11/15 17:30
 * @description C-服务实现逻辑
 */
@Component
public class QryCProcessor implements QryProcessor {
    @Override
    public boolean check(Map<String, String> request, Map<String, String> result) {
        if(StringUtils.equals(request.get("type"),"qryCProcessor") || Objects.isNull(request.get("type"))){
            return true;
        }
        return false;
    }

    @Override
    public void handle(Map<String, String> request, Map<String, String> result) {
        result.put("C","item_C");
    }
}
