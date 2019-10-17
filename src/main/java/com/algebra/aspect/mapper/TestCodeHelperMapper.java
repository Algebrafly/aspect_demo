package com.algebra.aspect.mapper;

import com.algebra.aspect.domain.codehelper.TestCodeHelper;
import org.apache.ibatis.annotations.Mapper;

/**
  * @author al
  * @date 2019/10/15 15:47
  * @description 
  */
@Mapper
public interface TestCodeHelperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestCodeHelper record);

    int insertSelective(TestCodeHelper record);

    TestCodeHelper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestCodeHelper record);

    int updateByPrimaryKey(TestCodeHelper record);
}