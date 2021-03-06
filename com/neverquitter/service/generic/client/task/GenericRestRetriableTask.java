package com.neverquitter.service.generic.client.task;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class GenericRestRetriableTask<T> {

	private ExecutorService executor;
	private Callable<T> task;
	public static final int DEFAULT_NUMBER_OF_RETRIES = 5;
	public static final long DEFAULT_WAIT_TIME = 1000;

	private int numberOfRetries; // total number of tries
	private int numberOfTriesLeft; // number left
	private long timeToWait; // wait interval

	public GenericRestRetriableTask(Callable<T> task,ExecutorService executor) {
		this(DEFAULT_NUMBER_OF_RETRIES, DEFAULT_WAIT_TIME, task, executor);
	}

	public GenericRestRetriableTask(int numberOfRetries, long timeToWait, Callable<T> task,ExecutorService executor) {
		this.numberOfRetries = numberOfRetries;
		numberOfTriesLeft = numberOfRetries;
		this.timeToWait = timeToWait;
		this.task = task;
		this.executor=executor;
	}

	public Future<T> retryAndCheckStatus() throws Exception {
		while (true) {
			try {
				return executor.submit(task);
					} catch (CancellationException e) {
				throw e;
			} catch (Exception e) {
				numberOfTriesLeft--;
				if (numberOfTriesLeft == 0) {
					// throw new RetryException
					System.out.println(numberOfRetries
							+ " attempts to retry failed at " + timeToWait
							+ "ms interval");
					throw new Exception("cannot run this tasks");
				}
				Thread.sleep(timeToWait);
			}
		}
	}
}