package com.neverquitter.service.generic.client.monitor.impl;

import com.neverquitter.service.generic.client.monitor.Guard;
import com.neverquitter.service.generic.client.monitor.ServiceHealth;
//immutable 
public enum ServiceHealthCheck implements ServiceHealth {

	HEALTH_CHECK_INSTANCE;
	private final static Guard guard = ServiceGuard.GUARD_INSTANCE;

	public Guard getGuard() {
		return guard;
	}
 
	public boolean isHealthy(final String service, final short failureCount, final long breakerTimer) {
		return this.getGuard().checkServiceHealth(service,failureCount,breakerTimer);
	}

	public void updateFailure(String service) {
		this.getGuard().updateGateKeeper(service);
	 
	}
}