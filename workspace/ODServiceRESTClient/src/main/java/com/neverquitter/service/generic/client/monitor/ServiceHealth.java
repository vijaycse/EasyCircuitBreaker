package com.neverquitter.service.generic.client.monitor;

public interface ServiceHealth {
	
	boolean isHealthy(String service,short failureCount,long breakerTimer);
	 void updateFailure(String service);
 
}
