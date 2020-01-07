package com.algebra.aspect.mapper;

import java.util.Map;

/**
 * @author al
 * @date 2020/1/6 16:49
 * @description 存储过程调用DAO
 */
public interface MyProcedureMapper {
    /**
     * 存储过程调用：获取两个数的和
     * @param param 参数集合
     * @return map 结果集合
     */
    Map<String,Object> getSum(Map<String,Object> param);
}
