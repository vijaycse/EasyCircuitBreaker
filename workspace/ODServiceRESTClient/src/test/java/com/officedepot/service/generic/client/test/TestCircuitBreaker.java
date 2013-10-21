package com.officedepot.service.generic.client.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.neverquitter.service.generic.client.monitor.CircuitBreaker;
import com.neverquitter.service.generic.client.monitor.Guard;
import com.neverquitter.service.generic.client.monitor.impl.BrokenServiceTimeKeeper;
import com.neverquitter.service.generic.client.monitor.impl.ServiceGuard;

public class TestCircuitBreaker {

	 
	private CircuitBreaker circuitBreakerTimeKeeper;
	private Guard guard;
	
	
	@Before
	public void setup() {
		circuitBreakerTimeKeeper = BrokenServiceTimeKeeper.CIRCUIT_BREAKER_INSTANCE;
		guard = ServiceGuard.GUARD_INSTANCE;
	}
	
	@Test
	public void testInvokeRestTemplateMockingHealthCheckWithTimer() throws InterruptedException{
		guard.updateGateKeeper("Test");
		guard.updateGateKeeper("Test");
		guard.updateGateKeeper("Test");
		assertFalse(guard.checkServiceHealth("Test", (short)2,  1000)); // health shd be bad
		Thread.sleep(1200); // setting 1.2 sec
		assertTrue(guard.checkServiceHealth("Test",(short)2,  1000));// health shd be good
		guard.updateGateKeeper("Test");
		assertTrue(guard.checkServiceHealth("Test",(short)2,  1000)); // health shd be good
	
	}
	
}