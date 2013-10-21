package com.officedepot.service.generic.client.load.test;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.junit.ParallelRunner;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParallelRunner.class)
public class TestGenericClientPerformanceContiPerf {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	@Test
	//@PerfTest(invocations = 1000, threads = 20)
	@PerfTest(duration = 36000, threads = 20,timer=RandomTimer.class, timerParams = { 500,800 })
	@Required(max = 8000, average = 1000)
	public void TestGenericClientWithBazzarVoiceQnA() {
		TestGenericClientForBazaarVoicePerf testBazzarVoice = new TestGenericClientForBazaarVoicePerf();
		testBazzarVoice.setup();
		testBazzarVoice.testInvokeRestTemplateWithBazaarVoiceWithQnA();
	}

	@Test
	//@PerfTest(invocations = 1000, threads = 20)
	@PerfTest(duration = 36000, threads = 20,timer=RandomTimer.class, timerParams = { 500,800 })
	@Required(max = 8000, average = 1000)
	public void TestGenericClientWithBazzarVoiceReview() {
		TestGenericClientForBazaarVoicePerf testBazzarVoice = new TestGenericClientForBazaarVoicePerf();
		testBazzarVoice.setup();
		testBazzarVoice.testInvokeRestTemplateWithBazaarVoiceWithReviews();
	}

	@Test
	//@PerfTest(invocations = 500, threads = 20)
	@PerfTest(duration = 36000, threads = 20,timer=RandomTimer.class, timerParams = { 500,800 })
	@Required(max = 8000, average = 4000)
	public void TestGenericClientWithStoreLocatorCode() {
		TestGenericClientForStoreLocatorPerf testStoreLocator = new TestGenericClientForStoreLocatorPerf();
		testStoreLocator.setup();
		testStoreLocator.testInvokeRestTemplateWithStoreLocatorCode();
	}


	@Test
	//@PerfTest(invocations = 500, threads = 20)
	@PerfTest(duration = 36000, threads = 20,timer=RandomTimer.class, timerParams = { 500,800 })
	@Required(max = 8000, average = 4000)
	public void TestGenericClientWithStoreLocatorAddress() {
		TestGenericClientForStoreLocatorPerf testStoreLocator = new TestGenericClientForStoreLocatorPerf();
		testStoreLocator.setup();
		testStoreLocator.testInvokeRestTemplateWithStoreLocatorAddress();
	}


	@Test
	//@PerfTest(invocations = 500, threads = 20)
	@PerfTest(duration = 36000, threads = 20,timer=RandomTimer.class, timerParams = { 500,800 })
	@Required(max = 20000, average = 13000)
	public void TestGenericClientWithPayPal() {
		TestGenericClientForPayPalPerf tstPaypal = new TestGenericClientForPayPalPerf();
		tstPaypal.setup();
		tstPaypal.testInvokeRestTemplateWithPayPalInitSession();
	}

}
