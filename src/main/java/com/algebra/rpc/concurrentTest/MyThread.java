package com.algebra.rpc.concurrentTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author al
 * @date 2019/7/26 9:02
 * @description
 */
public class MyThread implements Runnable {

    /**
     * 多线程结束后，执行后面的代码（计算时间、数量）
     */
    private CountDownLatch countDownLatch;

    /**
     * post请求参数
     */
    private String jsonParam;
    /**
     * 请求地址
     */
    private String url;

    public MyThread(){}

    public MyThread(String jsonParam, String url, CountDownLatch countDownLatch) {
        this.jsonParam = jsonParam;
        this.url = url;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            PostRequest.postRequestTest(url, jsonParam);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }
}
