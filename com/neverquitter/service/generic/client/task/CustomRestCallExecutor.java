package com.neverquitter.service.generic.client.task;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public enum CustomRestCallExecutor {

	REST_CALL_EXECUTOR;

	private final static Logger LOGGER = Logger.getLogger(CustomRestCallExecutor.class);
	private final static Map<String, CustomRestClientThreadPoolExecutor> THREAD_POOL_REGISTER = new ConcurrentHashMap<String, CustomRestClientThreadPoolExecutor>();


	/***
	 *  This obtains an instance of the threadpool executor
	 *  Note :creating thread pool queue size same as maxthreads by convention..
	 * @param minThreads
	 * @param maxThreads
	 * @param timeout
	 * @param queueSize
	 * @param serviceName
	 * @return
	 */
	public  CustomRestClientThreadPoolExecutor fetchODThreadPoolInstance(final short  minThreads, final short maxThreads, final long timeout,final String serviceName,final short queueSize) {
		if(!THREAD_POOL_REGISTER.containsKey(serviceName)){ 
			createThreadPool(serviceName,minThreads,maxThreads,timeout,queueSize);
		}
		return THREAD_POOL_REGISTER.get(serviceName);
	}


	/**
	 * 
	 * @param minThreads
	 * @param maxThreads
	 * @param timeout
	 * @param queueSize
	 * @return
	 */
	private void createThreadPool(String serviceName,short minThreads, short maxThreads, long timeout, short queueSize) {
		if(null==THREAD_POOL_REGISTER.get(serviceName)){ //Simultaneous access
			synchronized(this){
				if(null==THREAD_POOL_REGISTER.get(serviceName)){
					LOGGER.debug("Creating thread pool instance for " + serviceName + " with  minThreads " + minThreads +   " maxThreads " + maxThreads + " queue size " + queueSize);
					CustomRestClientThreadPoolExecutor odThreadPoolExecutor = new CustomRestClientThreadPoolExecutor(minThreads, maxThreads, timeout, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
					THREAD_POOL_REGISTER.put(serviceName, odThreadPoolExecutor);
				}
			}
		}
	}

}
