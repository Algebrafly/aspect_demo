package com.algebra.others.rpc.server;


/**
 * @author al
 * @date 2019/6/15 17:01
 * @description
 */
public interface MyServer {

    void start();

    void stop();

    void register(Class service, Class serviceImpl);

}
