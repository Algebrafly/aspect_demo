package com.algebra.basic.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author al
 * @date 2020/1/13 13:15
 * @description
 */
public class ThreadPoolManager2 {

    private static ThreadPoolManager2 sThreadPoolManager = new ThreadPoolManager2();

    // 线程池维护线程的最少数量
    private static final int SIZE_CORE_POOL = 5;

    // 线程池维护线程的最大数量
    private static final int SIZE_MAX_POOL = 10;

    /**
     * 线程池单例创建方法
     */
    public static ThreadPoolManager2 newInstance() {
        return sThreadPoolManager;
    }

    ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    private final ThreadPoolExecutor service = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, 0L,
                                           TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                                           factory,new ThreadPoolExecutor.AbortPolicy());
    /**
     * 线程池 // 实质就是newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * @param corePoolSize - 池中所保存的线程数，包括空闲线程。
     * @param maximumPoolSize - 池中允许的最大线程数。
     * @param keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
     * @param unit - keepAliveTime 参数的时间单位。
     * @param workQueue - 执行前用于保持任务的队列。此队列仅由保持 execute 方法提交的 Runnable 任务。
     * @param handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
     */
//    private final ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, 0L,
//            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


    /**
     * 将构造方法访问修饰符设为私有，禁止任意实例化。
     */
    private ThreadPoolManager2() {
    }

    /**
     * 将线程池初始化，核心线程数量
     */
    public void perpare() {
        if (service.isShutdown() && !service.prestartCoreThread()) {
            @SuppressWarnings("unused")
            int startThread = service.prestartAllCoreThreads();
        }
    }

    /**
     * 向线程池中添加任务方法
     */
    public void addExecuteTask(Runnable task) {
        if (task != null) {
            service.execute(task);
        }
    }

    /**
     * 判断是否是最后一个任务
     */
    protected boolean isTaskEnd() {
        if (service.getActiveCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取缓存大小
     */
    public int getQueue(){
        return service.getQueue().size();
    }

    /**
     * 获取线程池中的线程数目
     */
    public int getPoolSize(){
        return service.getPoolSize();
    }

    /**
     * 获取已完成的任务数
     */
    public long getCompletedTaskCount(){
        return service.getCompletedTaskCount();
    }

    /**
     * 关闭线程池，不在接受新的任务，会把已接受的任务执行玩
     */
    public void shutdown() {
        service.shutdown();
    }

}
