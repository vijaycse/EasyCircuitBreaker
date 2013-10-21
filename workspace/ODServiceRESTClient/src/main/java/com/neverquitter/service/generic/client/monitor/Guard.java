package com.neverquitter.service.generic.client.monitor;


public interface Guard {
	boolean checkServiceHealth(String service,short failureCount,long breakerTimer);
	void updateGateKeeper(final String service);
 
	}
