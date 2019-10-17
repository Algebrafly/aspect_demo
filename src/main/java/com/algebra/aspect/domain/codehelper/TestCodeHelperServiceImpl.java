package com.algebra.aspect.domain.codehelper;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.algebra.aspect.mapper.TestCodeHelperMapper;
import com.algebra.aspect.domain.codehelper.TestCodeHelper;
import com.algebra.aspect.domain.codehelper.TestCodeHelperService;
/**
  * @author al
  * @date 2019/10/15 15:47
  * @description 
  */
@Service
public class TestCodeHelperServiceImpl implements TestCodeHelperService{

    @Resource
    private TestCodeHelperMapper testCodeHelperMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return testCodeHelperMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TestCodeHelper record) {
        return testCodeHelperMapper.insert(record);
    }

    @Override
    public int insertSelective(TestCodeHelper record) {
        return testCodeHelperMapper.insertSelective(record);
    }

    @Override
    public TestCodeHelper selectByPrimaryKey(Integer id) {
        return testCodeHelperMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TestCodeHelper record) {
        return testCodeHelperMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TestCodeHelper record) {
        return testCodeHelperMapper.updateByPrimaryKey(record);
    }

}
