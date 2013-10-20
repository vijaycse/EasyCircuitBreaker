package com.neverquitter.service.generic.client.monitor.impl;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.neverquitter.service.generic.client.monitor.CircuitBreaker;
import com.officedepot.service.generic.client.model.ServiceMonitor;

public enum BrokenServiceTimeKeeper implements CircuitBreaker {

	CIRCUIT_BREAKER_INSTANCE;

	private final static Logger LOGGER = Logger.getLogger(BrokenServiceTimeKeeper.class);
	
	private long durationToStop;

	public long getDurationToStop() {
		return durationToStop;
	}

	public void setDurationToStop(long durationToStop) {
		this.durationToStop = durationToStop;
	}

	/**
	 * setting timer
	 */
	public void setBrokenServiceTimer(final ServiceMonitor serviceMonitor) {
		Timer timer = new Timer("ServiceBroke");
		if (serviceMonitor.isServiceBroke()) {
			timer.schedule(new BrokenServiceTimerTask(serviceMonitor, timer), this.getDurationToStop());  
			LOGGER.error("Service is shutdown. Please try after some time....Starting time " + System.currentTimeMillis() + " duration of the break " 
			+ this.getDurationToStop() + " default " + this.getDurationToStop());
		}
	}

	/**
	 * 
	 * Class that creates a timer task which will be running for the
	 * durationtostop value
	 * 
	 */

	class BrokenServiceTimerTask extends TimerTask {
		private final Timer timer;
		private final ServiceMonitor serviceMonitor;

		BrokenServiceTimerTask(final ServiceMonitor serviceMonitor,	final Timer timer) {
			this.timer = timer;
			this.serviceMonitor = serviceMonitor;
		}

		@Override
		public void run() {
			short resetFailure = 0;
			serviceMonitor.setServiceBroke(false);
			serviceMonitor.setFailureCount(resetFailure);
			timer.cancel();
			LOGGER.error("Circuit Breaker is Reset.Service might be restored.. Please try..End Time " + System.currentTimeMillis());
		}
	}

}
