package com.officedepot.service.generic.client.test;

import static org.junit.Assert.*;

/**
 * Please run java based test cases if groovy is not installed
 */

 
import com.officedepot.service.generic.client.monitor.CircuitBreaker;
import com.officedepot.service.generic.client.monitor.impl.BrokenServiceTimeKeeper;
import com.officedepot.service.generic.client.monitor.impl.ServiceGuard;




class TestCircuitBreakerGroovy  extends GroovyTestCase{

	def circuitBreakerTimeKeeper
	def guard
	 
	void setUp() {
		circuitBreakerTimeKeeper = BrokenServiceTimeKeeper.CIRCUIT_BREAKER_INSTANCE
		circuitBreakerTimeKeeper.setDurationToStop(1000) // max 1 sec
		guard = ServiceGuard.GUARD_INSTANCE
		//guard.setCircuitBreaker(circuitBreakerTimeKeeper)
		guard.setMaxFailureCount((short)2) // max two failures
	}
	
 
	void testInvokeRestTemplateMockingHealthCheckWithTimer() throws InterruptedException{
		3.times {guard.updateGateKeeper("Test")}
		assertFalse guard.checkServiceHealth("Test") // health shd be bad
		Thread.sleep(1200) // setting 1.2 sec
		assertTrue guard.checkServiceHealth("Test") // health shd be good
		guard.updateGateKeeper("Test")
		assertTrue guard.checkServiceHealth("Test") // health shd be good
	
	}
	
}
