package com.algebra.aspect.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author al
 * @date 2019/12/11 9:04
 * @description 自定义异常信息枚举类
 */
public enum ErrorCodeEnum {

    SYS_ERROR("SYS_ERROR", "系统错误，请重试"),
    UNKNOWN_ERROR("UNKNOWN_SYS_ERROR", "未知的系统异常"),
    SERVICE_INVOKE_FAIL("SERVICE_INVOKE_FAIL", "服务调用失败"),
    ILLEGAL_ARGS("ILLEGAL_ARGS", "参数校验错误"),
    ;

    public static ErrorCodeEnum getByValue(String code) {
        for (ErrorCodeEnum result : values()) {
            System.out.println(result.ordinal());
            if (StringUtils.equals(result.getCode(), code)) {
                return result;
            }
        }
        return null;
    }

    private String code;
    private String desc;

    ErrorCodeEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }


}
