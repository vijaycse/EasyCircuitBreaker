package com.officedepot.service.generic.client.test;

import static org.junit.Assert.*;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException
import com.officedepot.service.generic.client.monitor.impl.BrokenServiceTimeKeeper;
import com.officedepot.service.generic.client.monitor.impl.ServiceGuard;
import com.officedepot.service.generic.client.monitor.impl.ServiceHealthCheck;
import com.officedepot.services.storelocator.model.StoreRouteInfo

/**
 * Please run java based test cases if groovy is not installed
 */


class TestGenericClientHealthCheckGroovy  extends GroovyTestCase{

	
	def circuitBreakerTimeKeeper
	def guard
	def template
	
	void setUp() {
		circuitBreakerTimeKeeper =BrokenServiceTimeKeeper.CIRCUIT_BREAKER_INSTANCE 
		circuitBreakerTimeKeeper.setDurationToStop(1000) // max 1 sec
		guard = ServiceGuard.GUARD_INSTANCE
		//guard.setCircuitBreaker(circuitBreakerTimeKeeper)
		guard.setMaxFailureCount((short)2) // max two failures
		template = ODGenericClientExecutor.clientExecutorInstance();
	}
	
	void testInvokeRestTemplateHealthCheck()
	throws GenericClientException {
		def StoreRouteInfo storeInfo
		def params = new HashMap<String, String>()
		params.put("fromCity", "boca raton")
		params.put("fromState", "FL")
		params.put("toCity", "delay beach")
		params.put("toState", "FL")
		
		try{
		storeInfo = template
					.invokeRestTemplate(
					"http://odservicesdev6.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
					params, StoreRouteInfo.class);
			System.out0(" store route info " + storeInfo.toString())
		}
		catch(GenericClientException g1){
			try{
				storeInfo = template
						.invokeRestTemplate(
						"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
						params, StoreRouteInfo.class)
				LOGGER.debug(" store route info " + storeInfo.toString())
			}
			catch(GenericClientException g2) {
				shouldFail {
				storeInfo = template
						.invokeRestTemplate(
						"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
						params, StoreRouteInfo.class)
				LOGGER.debug(" store route info " + storeInfo.toString())
				}
			}
		}
	}
}



