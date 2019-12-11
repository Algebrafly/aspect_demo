package com.algebra.aspect.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2019/12/11 9:09
 * @description 自定义业务异常
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码枚举类
     */
    private ErrorCodeEnum errorCode;

    /**
     * 详细错误信息
     */
    private Map<String, String> errorMap = new HashMap<>();


    /**
     * 带参构造器.
     * 进展是枚举类异常码和描述信息
     * @param errorCode 枚举类异常码
     */
    public BusinessException(ErrorCodeEnum errorCode) {
        super(errorCode.getDesc());
        this.setErrorCode(errorCode);
    }
    /**
     * 带参构造器.
     * 新增异常详细信息
     * @param errorCode 枚举类异常码
     * @param message 异常详细信息
     */
    public BusinessException(ErrorCodeEnum errorCode, String message) {
        super(StringUtils.isNotBlank(message) ? message : errorCode.getDesc());
        this.setErrorCode(errorCode);
    }

    /**
     * 带参构造器.
     * 新增异常信息map
     * @param errorCode 枚举类异常码
     * @param errorMap 异常信息map
     */
    public BusinessException(ErrorCodeEnum errorCode, Map<String, String> errorMap) {
        this(errorCode);
        this.errorMap = errorMap;
    }
    /**
     * 带参构造器.
     * @param message 异常详细信息
     */
    public BusinessException(String message) {
        super(message);
        this.setErrorCode(ErrorCodeEnum.UNKNOWN_ERROR);
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }
    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * Gets error map.
     *
     * @return the error map
     */
    public Map<String, String> getErrorMap() {
        return errorMap;
    }
    /**
     * Sets error map.
     *
     * @param errorMap the error map
     */
    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    private static String findMessage(Map<String, String> errorMap) {
        if (errorMap.isEmpty()) {
            return null;
        }
        return errorMap.values().iterator().next();
    }

}
