package com.algebra.aspect.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author al
 * @date 2019/8/22 16:05
 * @description
 */
@Data
@ApiModel("用户操作类VO")
public class UserDto {

    @ApiModelProperty(value = "userName",dataType = "String")
    @JsonProperty(value = "userName")
    private String userName;

    @ApiModelProperty(value = "iconUrl",dataType = "String")
    @JsonProperty(value = "iconUrl")
    private String iconUrl;

}
