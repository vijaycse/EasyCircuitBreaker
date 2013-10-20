package com.neverquitter.service.generic.client.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.officedepot.service.generic.client.ODGenericThreadPool;

public class GenericRestClientThreadPool implements ODGenericThreadPool {

	private ExecutorService executor;
	private final static Logger LOGGER = Logger
			.getLogger(GenericRestClientThreadPool.class);
	private final static int MIN_THREADS = 5;
	private final static int MAX_THREADS = 10;
	
	/**
	 * Creates a thread pool executor with core size and maximum size specify a
	 * time out and pass the blocking queue Create a blocking queue to hold work
	 * items. Decouples rejection policy is set to CallerRunsPolicy... to assign
	 * the rejected task to the parent thread
	 */
 

	public GenericRestClientThreadPool() {
 

		/**
		 * Although newCachedThreadPool is the most suggested way, it uses
		 * synchronous blocking queue where it operates on nonfairness policy
		 * and it has got 0 capacity for holding the task that is awaiting.
		 */
		// executor = Executors.newCachedThreadPool();
		executor = new ThreadPoolExecutor(MIN_THREADS, MAX_THREADS,
				Long.MAX_VALUE, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(MAX_THREADS, true),
				new ThreadPoolExecutor.CallerRunsPolicy());
		LOGGER.debug("Thread Pool created with " + MAX_THREADS + " max threads");

		/**
		 * on purpose, commenting out as retry logic is not recommended
		 */
		// executor.setRejectedExecutionHandler(new RejectedHandler());
	}

	/**
	 * Submit callable to thread pool executor
	 */
	public Future<?> submitTasks(Callable<?> task) {
		return executor.submit(task);
	}

	/**
	 * Retrieving the blocked tasks. From sun doc, it is not advisable to use
	 * the retry logic instead it suggests to use abort policy or
	 * CallerRunsPolicy. this is UNUSED NOW but if needed we can turn it on by
	 * setRejectedExecutionHandler as shown above Reference :
	 * http://stackoverflow
	 * .com/questions/3446011/threadpoolexecutor-block-when-queue
	 * -is-full/3518588#3518588
	 * 
	 */

	/*
	 * @SuppressWarnings("unused") private class RejectedHandler implements
	 * RejectedExecutionHandler {
	 * 
	 * public void rejectedExecution(Runnable task, ThreadPoolExecutor executor)
	 * { // TODO Add blocking Queue to keep adding rejected tasks and try to //
	 * resubmit in the interval try { // creating retriable task if (new
	 * RetriableTask(task, executor).retryAndCheckStatus())
	 * System.err.println(Thread.currentThread().getName() +
	 * " execution rejected: " + task); } catch (Exception e) { // TODO throw
	 * RetryException e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * }
	 */

}
