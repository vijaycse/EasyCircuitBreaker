package com.neverquitter.service.generic.client.monitor;

import com.officedepot.service.generic.client.model.ServiceMonitor;

public interface CircuitBreaker {
	void setBrokenServiceTimer(ServiceMonitor serviceMonitor);
	void setDurationToStop(long durationToStop);
	}
