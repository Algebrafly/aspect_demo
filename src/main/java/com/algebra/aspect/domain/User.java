package com.algebra.aspect.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户操作类")
public class User implements Serializable {

    @ApiModelProperty(value = "用户id",dataType = "String")
    private String uId;

    @ApiModelProperty(value = "用户姓名",dataType = "String")
    private String uName;

    @ApiModelProperty(value = "用户登录密码",dataType = "String")
    private String password;

    @ApiModelProperty(value = "用户证件类型",dataType = "String")
    private String idTyp;

    @ApiModelProperty(value = "用户证件号",dataType = "String")
    private String idNum;

    @ApiModelProperty(value = "用户手机号",dataType = "String")
    private String phone;

    @ApiModelProperty(value = "用户状态",dataType = "String")
    private String status;

    @ApiModelProperty(value = "备注",dataType = "String")
    private String remark;

}