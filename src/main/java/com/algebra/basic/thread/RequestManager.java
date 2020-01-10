package com.algebra.basic.thread;

import javax.servlet.http.HttpServletRequest;

/**
 * @author al
 * @date 2020/1/10 14:13
 * @description Http请求管理工具类：ThreadLocal应用
 */
public class RequestManager {
    private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

    /**
     * 在当前线程中加入request
     * @param request 请求
     */
    public static void setHttpServletRequest(HttpServletRequest request){
        if(request != null){
            threadLocal.set(request);
        }
    }

    /**
     * 当前线程获取request,在API接口中可以直接调用这个方法获取当前线程的request对象
     * @return 请求
     */
    public static HttpServletRequest getHttpServletRequest(){
        return threadLocal.get();
    }

    /**
     * 清理request，释放空间
     */
    public static void removeHttpServletRequest(){
        threadLocal.remove();
    }
}
