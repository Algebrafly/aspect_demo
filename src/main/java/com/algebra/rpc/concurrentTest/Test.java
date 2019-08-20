package com.algebra.rpc.concurrentTest;

import com.algebra.aspect.domain.User;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author al
 * @date 2019/7/26 9:02
 * @description
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        String url = "http://127.0.0.1:8182/asp-demo/insertUser";

        long begaintime = System.currentTimeMillis();

        //线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        final int count = 50;
        //与countDownLatch.await();实现运行完所有线程之后才执行后面的操作
        CountDownLatch countDownLatch = new CountDownLatch(count);
        //final CyclicBarrier barrier = new CyclicBarrier(count);  //与barrier.await() 实现并发;
        //创建100个线程
        for(int i = 0; i < count; i++){
            User user = new User();
            user.setUId("u_007"+i);
            user.setUName("张小敬"+i);
            user.setPassword("66666"+i);
            user.setIdNum("686868686");
            user.setIdTyp("1");
            user.setStatus("1");
            user.setRemark("长安十二时辰");

            String jsonStr = "{\"idNum\":\"1\",\"idTyp\":\"1\",\"password\":\"1\",\"phone\":\"1\",\"remark\":\"1\",\"status\":\"1\",\"uid\":\"u_00\""+i+",\"uname\":\"檀棋\"}";
            jsonStr = JSONObject.toJSONString(user);
            System.out.println(jsonStr);

            MyThread target = new MyThread(jsonStr, url, countDownLatch);

            //barrier.await();
            pool.execute(target);
        }
        pool.shutdown();
        try {
            //这一步是为了将全部线程任务执行完以后，开始执行后面的任务（计算时间，数量）
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(count + " 个  接口请求总耗时 ： "+(endTime-begaintime)+"-----平均耗时为"+ ((endTime-begaintime)/count));
    }

}
