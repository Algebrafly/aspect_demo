package com.algebra.aspect.domain.codehelper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.algebra.aspect.domain.codehelper.Person;

@Mapper
public interface PersonDao {
    int insert(@Param("pojo") Person pojo);

    int insertSelective(@Param("pojo") Person pojo);

    int insertList(@Param("pojos") List<Person> pojo);

    int update(@Param("pojo") Person pojo);
}
