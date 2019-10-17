package com.algebra.aspect.domain.codehelper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
  * @author al
  * @date 2019/10/15 15:47
  * @description 
  */
@ApiModel(value="com.algebra.aspect.domain.codehelper.TestCodeHelper")
@Data
public class TestCodeHelper implements Serializable {
    @ApiModelProperty(value="null")
    private Integer id;

    @ApiModelProperty(value="null")
    private String name;

    @ApiModelProperty(value="null")
    private String remark;

    private static final long serialVersionUID = 1L;
}