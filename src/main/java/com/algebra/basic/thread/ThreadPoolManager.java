package com.algebra.basic.thread;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.*;

/**
 * @author al
 * @date 2020/1/13 9:59
 * @description 可根据Context控制任务生命周期线程池管理类
 * 异步任务的执行结果可通过事件管理中心(EventManager)派发到UI主线程中
 */
public class ThreadPoolManager {

    /**
     * 线程池
     */
    private ThreadPoolExecutor threadPool;
    /**
     * 可以控制任务生命周期的任务队列
     */
    private Map<String, List<WeakReference<Future<?>>>> taskMap;
    private String className;
    private static ThreadPoolManager instance = null;

    /**
     * 创建一个新的实例 ThreadPoolManager.
     *
     * @param className 包含包名的类的全名称
     */
    public ThreadPoolManager(String className) {
        this.className = className;
        threadPool = (ThreadPoolExecutor) Executors
                .newCachedThreadPool();
        taskMap = new WeakHashMap<>();
    }

    /**
     * 获取ThreadPoolManager单例对象
     */
    public static ThreadPoolManager getInstance(String className) {
        if (instance == null) {
            synchronized (ThreadPoolManager.class) {
                if (instance == null) {
                    instance = new ThreadPoolManager(className);
                }
            }
        }
        instance.className = className;
        return instance;
    }

    /**
     * 释放ThreadPoolManager单例对象
     * void
     *
     * @throws
     * @since 1.0.0
     */
    public static void release() {
        synchronized (ThreadPoolManager.class) {
            if (instance != null) {
                instance.cancelAllTaskThreads();
            }
            instance = null;
        }
    }

    /**
     * 执行在给定延迟后启用的一次性操作
     *
     * @param command 要执行的任务
     * @param delay   从现在开始延迟执行的时间
     * @param unit    延迟参数的时间单位
     *                void
     * @throws
     * @since 1.0.0
     */
    public void schedule(Runnable command, long delay, TimeUnit unit) {
        if (threadPool instanceof ScheduledExecutorService) {
            Future<?> request = ((ScheduledExecutorService) threadPool).schedule(command, delay, unit);
            addTask(request);
        }
    }

    /**
     * 执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期
     *
     * @param command      要执行的任务
     * @param initialDelay 首次执行的延迟时间
     * @param period       连续执行之间的周期
     * @param unit         参数的时间单位
     *                     void
     * @throws
     * @since 1.0.0
     */
    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        if (threadPool instanceof ScheduledExecutorService) {
            Future<?> request = ((ScheduledExecutorService) threadPool).scheduleAtFixedRate(command, initialDelay, period, unit);
            addTask(request);
        }
    }

    /**
     * 开启线程
     */
    public void startTaskThread(Runnable runnable) {
        Future<?> request = threadPool.submit(runnable);
        addTask(request);
    }

    /**
     * 添加执行任务到队列中
     */
    private void addTask(Future<?> request) {
        synchronized (ThreadPoolManager.class) {
            if (className != null) {
                // 在请求集中添加本次请求
                List<WeakReference<Future<?>>> requestList = taskMap.get(className);
                if (requestList == null) {
                    requestList = new LinkedList<>();
                    taskMap.put(className, requestList);
                }
                requestList.add(new WeakReference<>(request));
            }
        }
    }

    /**
     * 在OnDestroy中结束所有开启的线程
     *
     * @param className             对应的包含包名的类名
     * @param mayInterruptIfRunning 是否关闭正在运行的线程标志
     *                              void
     * @throws
     * @since 1.0.0
     */
    public void cancelTaskThreads(String className,
                                  boolean mayInterruptIfRunning) {
        synchronized (ThreadPoolManager.class) {
            List<WeakReference<Future<?>>> requestList = taskMap
                    .get(className);
            if (requestList != null) {
                for (WeakReference<Future<?>> requestRef : requestList) {
                    Future<?> request = requestRef.get();
                    if (request != null) {
                        request.cancel(mayInterruptIfRunning);
                    }
                }
            }
            taskMap.remove(className);
        }
    }

    /**
     * 取消所有的任务
     * void
     *
     * @throws
     * @since 1.0.0
     */
    private void cancelAllTaskThreads() {
        for (String clsName : taskMap.keySet()) {
            List<WeakReference<Future<?>>> requestList = taskMap.get(clsName);
            if (requestList != null) {
                Iterator<WeakReference<Future<?>>> iterator = requestList.iterator();
                while (iterator.hasNext()) {
                    Future<?> request = iterator.next().get();
                    if (request != null) {
                        request.cancel(true);
                    }

                }
            }
        }
        taskMap.clear();
    }

}
