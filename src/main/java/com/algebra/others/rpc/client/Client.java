package com.algebra.others.rpc.client;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author al
 * @date 2019/6/15 17:14
 * @description
 */
@Slf4j
public class Client {

    /**
     * 获取代表服务端接口的动态代理对象
     * @param serviceIntf 接口名
     * @param addr 服务端地址:ip
     * @param <T>
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(Class serviceIntf, InetSocketAddress addr){
        /*
         * param:
         * 1.类加载器 - 需要代理的类
         * 2.需要代理的对象 -
         */

        /*
         * proxy - 代理对象
         * method - 代理方法
         * args - 参数列表
        * */
        InvocationHandler handler = (proxy, method, args) -> {

            ObjectInputStream in = null;
            ObjectOutputStream out = null;
            Socket socket= null;
            try {
                socket = new Socket();
                // socketAddress
                socket.connect(addr);
                out = new ObjectOutputStream(socket.getOutputStream());// 发送
                // 接口名 - 方法名 - 方法参数(多个) - 方法参数类型(多个)
                String str = "";
                out.writeUTF(serviceIntf.getName());
                out.writeUTF(method.getName());
                out.writeObject(method.getParameterTypes());
                out.writeObject(args);

                log.info("等待服务端处理...");
                // 接受服务端数据
                in = new ObjectInputStream(socket.getInputStream());

                return in.readObject();
            } catch (Exception e){
                e.printStackTrace();

            } finally {
                if(in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(out != null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return  null;
        };
        return (T) Proxy.newProxyInstance(serviceIntf.getClassLoader(),new Class[]{serviceIntf},handler);
    }

}
