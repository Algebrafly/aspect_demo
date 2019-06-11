package com.algebra.aspect.service;

import java.util.Date;

/**
 * @author al
 * @date 2019/6/10 19:40
 * @description 日志操作类
 */
public interface ILogService {

    void infoLog(String className, String methodName, String message, Date oprDate);

    void errorLog(String className, String methodName, String message, Date oprDate);

}
