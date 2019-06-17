package com.algebra.others.test.client;

import com.algebra.others.rpc.client.Client;
import com.algebra.others.test.service.IHelloService;

import java.net.InetSocketAddress;

/**
 * @author al
 * @date 2019/6/17 20:53
 * @description
 */
public class RPCClientTest {
    public static void main(String[] args) throws ClassNotFoundException {
        IHelloService service = Client.getRemoteProxyObj(Class.forName("com.algebra.others.test.service.IHelloService"),
                new InetSocketAddress("127.0.0.1",9999));
        String result = service.sayHello("孔子老师");

        System.out.println(result);

    }
}
