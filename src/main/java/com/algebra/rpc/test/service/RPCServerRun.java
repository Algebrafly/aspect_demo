package com.algebra.rpc.test.service;

import com.algebra.rpc.rpc.server.MyServer;
import com.algebra.rpc.rpc.server.impl.MyServerCenter;
import com.algebra.rpc.test.service.impl.HelloService;

/**
 * @author al
 * @date 2019/6/17 20:47
 * @description
 */
public class RPCServerRun {

    public static void main(String[] args) {

        // 开启一个线程--启动
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();

        MyServer server = new MyServerCenter(9999);
        server.register(IHelloService.class,HelloService.class);
        server.start();
    }
}
