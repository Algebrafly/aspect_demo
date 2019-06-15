package com.algebra.others.rpc.server.impl;

import com.algebra.others.rpc.server.MyServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author al
 * @date 2019/6/15 17:03
 * @description
 */
@Slf4j
public class MyServerCenter implements MyServer {

    /**
     * 模拟注册中心：存放服务端注册的接口
     * key - 接口名字
     * value - 接口实现类
     *
     */
    private static HashMap<String,Class> serviceRegister = new HashMap<>();

    /**
     * 端口号
     */
    private int port;

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public MyServerCenter(int port){
        this.port = port;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void start() {

        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        Socket socket= null;
        try {
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress(port));
            socket = server.accept();  // 客户端连接

            log.info("接受到客户端请求，等待处理...");
             in = new ObjectInputStream(socket.getInputStream());

            // 处理客户端发送的数据
            String serviceName = in.readUTF();
            String methodName = in.readUTF();
            Class[] paramTypes = (Class[]) in.readObject();
            Object[] args = (Object[]) in.readObject();

            // service接口 - 调用
            Class seviceClass = serviceRegister.get(serviceName);
            Method method = seviceClass.getMethod(methodName, paramTypes);
            Object result = method.invoke(seviceClass.newInstance(),args);

            // 返回值给客户端
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(result);

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
//            if(socket != null){
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void register(Class service, Class serviceImpl) {
        serviceRegister.put(service.getName(),serviceImpl);
    }
}
