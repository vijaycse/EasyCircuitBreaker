package com.neverquitter.service.generic.client.task;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public final class CustomRestClientThreadPoolExecutor extends ThreadPoolExecutor {
	
	
	
	private final Map<Runnable,Boolean> inProgress = new ConcurrentHashMap<Runnable,Boolean>();
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private long taskTime;
	private long totalTaskTime;
	private int totalTasks;
	private TaskTimeQueue timeQueue = new TaskTimeQueue();
	private long submitTime;
	private long timeInQueue;
	
	
	
	
	
	/**
	 * Note: CallerRunsPolicy - to assign the rejected task to the parent thread
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 */
	public CustomRestClientThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,new ThreadPoolExecutor.CallerRunsPolicy());

	}
	
	
	/**
	 * 
	 * @param task
	 * @return
	 */
	public <T> Future<T> submitTasks(Callable<T> task) {
		return super.submit(task);
	}
	
	
	
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		timeInQueue = System.currentTimeMillis() - submitTime;
		inProgress.put(r, Boolean.TRUE);
		startTime.set(new Long(System.currentTimeMillis()));
	}
	
	protected void afterExecute(Runnable r, Throwable t) {
		taskTime = System.currentTimeMillis() - startTime.get().longValue();
		synchronized (this) {
			totalTaskTime += taskTime;
			++totalTasks;
			timeQueue.add(taskTime);
		}
		inProgress.remove(r);
		super.afterExecute(r, t);
	}
	
	public Set<Runnable> getInProgressTasks() {
		return Collections.unmodifiableSet(inProgress.keySet());
	}
	
	public synchronized int getTotalTasks() {
		return totalTasks;
	}
	
	public synchronized double getAverageTaskTime() {
		return (totalTasks == 0) ? 0 : totalTaskTime / totalTasks;
	}

	public synchronized double getRecentAverageTaskTime(){
		Iterator<Long> itr = timeQueue.iterator();
		Long total = new Long(0); 
		while(itr.hasNext()){
			total += itr.next();
		}
		return total/TaskTimeQueue.CAPACITY; 
	}
	
	public synchronized double getTimeInQueue() {
		return timeInQueue;
	}
	
	public synchronized double getTotalTime() {
		return timeInQueue + taskTime;
	}
	 
	
		 
}



