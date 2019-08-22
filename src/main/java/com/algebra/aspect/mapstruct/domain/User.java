package com.algebra.aspect.mapstruct.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author al
 * @date 2019/8/22 16:04
 * @description
 */
@Data
@ApiModel("用户操作类DO")
public class User {

    @ApiModelProperty(value = "userName",dataType = "String")
    @JsonProperty(value = "userName")
    private String userName;
    @ApiModelProperty(value = "password",dataType = "String")
    @JsonProperty(value = "password")
    private String password;
    @ApiModelProperty(value = "iconUrl",dataType = "String")
    @JsonProperty(value = "iconUrl")
    private String iconUrl;


}
