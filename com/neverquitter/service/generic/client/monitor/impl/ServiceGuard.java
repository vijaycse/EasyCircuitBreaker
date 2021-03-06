package com.neverquitter.service.generic.client.monitor.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.neverquitter.service.generic.client.monitor.CircuitBreaker;
import com.neverquitter.service.generic.client.monitor.Guard;
import com.officedepot.service.generic.client.model.ServiceMonitor;

// immutable 

public enum ServiceGuard implements Guard {

	GUARD_INSTANCE;

	private final static ConcurrentHashMap<String, ServiceMonitor> gateKeeper = new ConcurrentHashMap<String, ServiceMonitor>();
	private final static Logger LOGGER = Logger.getLogger(ServiceGuard.class);
	private final static CircuitBreaker circuitBreaker = BrokenServiceTimeKeeper.CIRCUIT_BREAKER_INSTANCE;
	private final static ReentrantLock lock =  new ReentrantLock(); 

	public CircuitBreaker getCircuitBreaker() {
		return circuitBreaker;
	}

	/**
	 * updating the guard keeper with failures
	 */
	public void updateGateKeeper(final String service) {
		if (gateKeeper.containsKey(service)) {
			executeUpdateRegisterFailures(service);
		} else { // First time failure.
			lock.lock();
			try {	
				if(null==gateKeeper.get(service)){ // simulataneous access
					final ServiceMonitor serviceMonitor = new ServiceMonitor();
					serviceMonitor.setFailureCount((short)0);
					gateKeeper.putIfAbsent(service, serviceMonitor);
				}else {
					ServiceMonitor serviceMonitor = gateKeeper.get(service);
					short failureCount = serviceMonitor.getFailureCount();
					serviceMonitor.setFailureCount(++failureCount);
					gateKeeper.putIfAbsent(service, serviceMonitor);
				}
			}
			finally{
				lock.unlock();
			}
		}
	}



	/**
	 * 
	 * @param service
	 */
	private void executeUpdateRegisterFailures(String service) {
		short currentFailure=0;
		lock.lock();
		try{
			ServiceMonitor serviceMonitor = gateKeeper.get(service);
			currentFailure = serviceMonitor.getFailureCount();
			currentFailure++;
			if (!serviceMonitor.isServiceBroke()) { 
				LOGGER.debug("Service " + service + "failure count "+ currentFailure);
				serviceMonitor.setFailureCount(currentFailure);
				// Putifabsent is not needed but nothing wrong coding..
				gateKeeper.putIfAbsent(service, serviceMonitor);
			}// else do not update the record keeper. no further action
		}
		finally{
			lock.unlock();
		}
	}

	/**
	 * 
	 * @param Service
	 * @return
	 */
	private short getFailureCountFromGateKeeper(final String service) {
		final ServiceMonitor serviceMonitor = gateKeeper.get(service);
		return (serviceMonitor == null) ? 0 : serviceMonitor.getFailureCount();
	}

	/**
	 * checking the health of the service
	 * 
	 * @return boolean
	 */
	public boolean checkServiceHealth(final String service,final short failureCountAllowed, final long breakerTimer) {
		int currentFailureCount = getFailureCountFromGateKeeper(service);
		if (currentFailureCount <  failureCountAllowed) {
			if(currentFailureCount >0){ //reset previous failures,if any
				LOGGER.debug("resetting previous failures for " +service);
				ServiceMonitor serviceMonitor = gateKeeper.get(service);
				serviceMonitor.setFailureCount((short) 0);
				gateKeeper.putIfAbsent(service, serviceMonitor);
			}
			return true;
		} else {
			LOGGER.error("Circuit breaker is open now for " + service);
			LOGGER.error("Service " + service + " is going to be shut down soon");
			setServiceBroke(service,breakerTimer);
			return false;
		}

	}

	/**
	 * setting circuitbreaker for this service
	 * 
	 * @param service
	 */

	private synchronized void setServiceBroke(String service,long breakerTimer) {
		final ServiceMonitor serviceMonitor = gateKeeper.get(service);
		if(!serviceMonitor.isServiceBroke()) {
			serviceMonitor.setServiceBroke(true);
			this.getCircuitBreaker().setDurationToStop(breakerTimer);
			this.getCircuitBreaker().setBrokenServiceTimer(serviceMonitor);
		}
	}
}
