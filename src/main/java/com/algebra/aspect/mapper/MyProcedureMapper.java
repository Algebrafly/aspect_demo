package com.algebra.aspect.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.StatementType;

import java.util.Map;

/**
 * @author al
 * @date 2020/1/6 16:49
 * @description
 */
public interface MyProcedureMapper {
    /**
     * 存储过程调用
     * @param param 参数
     * @return map
     */
    Map<String,Object> getSum(Map<String,Object> param);
}
