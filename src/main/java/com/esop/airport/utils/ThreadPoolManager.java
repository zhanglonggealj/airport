package com.esop.airport.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池管理
 * 无界corePoolsize,0sizeBlockingQueue，适用于CPU密集型任务调度
 */
public class ThreadPoolManager {

	private ExecutorService service;
	private ExecutorService fixedThreadPool;

	private final static int MAX_THREADS = 200;

	private ThreadPoolManager() {
		service = Executors.newCachedThreadPool();
		fixedThreadPool = Executors.newFixedThreadPool(MAX_THREADS);
	}

	private static volatile ThreadPoolManager manager;

	public static ThreadPoolManager getInstance() {

		if (null == manager) {
			synchronized (ThreadPoolManager.class) {
				if (null == manager) {
					manager = new ThreadPoolManager();
				}
			}
		}

		return manager;
	}

	public void addCachedThreadTask(Runnable runnable) {
		service.submit(runnable);
	}

	public void addFixedThreadTask(Runnable runnable) {
		fixedThreadPool.submit(runnable);
	}

	/**
	 * 获取当前线程池工作线程数
	 * @return 返回-1 则当前线程池 异常
	 */
	public int getFixedThreadActiveCount() {

		try {

			int threadCount = ((ThreadPoolExecutor)fixedThreadPool).getActiveCount();

			return threadCount;
		} catch (Exception e) {
			e.printStackTrace();
			fixedThreadPool.isShutdown();
			return -1;
		}
	}

}
