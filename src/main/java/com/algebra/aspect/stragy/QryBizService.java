package com.algebra.aspect.stragy;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2019/11/15 17:40
 * @description
 */
@Component
public class QryBizService {

    private List<QryProcessor> qryProcessorList;

    private Map<String,QryProcessor> qryProcessorMap;

    public Map<String,String> query(String type){
        Map<String,String> request = Maps.newHashMap();
        Map<String,String> result = Maps.newHashMap();

        if(StringUtils.isNotBlank(type)){
            request.put("type",type);
        }

        qryProcessorList.forEach(f -> {
            if(f.check(request,result)){
                f.handle(request,result);
            }
        });
        return result;
    }

    public Map<String,String> queryMap(String type){
        Map<String,String> request = Maps.newHashMap();
        Map<String,String> result = Maps.newHashMap();

        if(StringUtils.isNotBlank(type)){
            request.put("type",type);
        }

        qryProcessorMap.get(type).handle(request,result);
        return result;
    }

}
