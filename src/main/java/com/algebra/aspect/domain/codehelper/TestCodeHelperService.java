package com.algebra.aspect.domain.codehelper;

import com.algebra.aspect.domain.codehelper.TestCodeHelper;
    /**
  * @author al
  * @date 2019/10/15 15:47
  * @description 
  */
public interface TestCodeHelperService{


    int deleteByPrimaryKey(Integer id);

    int insert(TestCodeHelper record);

    int insertSelective(TestCodeHelper record);

    TestCodeHelper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestCodeHelper record);

    int updateByPrimaryKey(TestCodeHelper record);

}
