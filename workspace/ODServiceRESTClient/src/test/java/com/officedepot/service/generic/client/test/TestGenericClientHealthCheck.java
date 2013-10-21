package com.officedepot.service.generic.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;
import com.officedepot.services.storelocator.model.StoreLocatorInfo;
import com.officedepot.services.storelocator.model.StoreRouteInfo;

public class TestGenericClientHealthCheck {

	private final static Logger LOGGER = Logger.getLogger(TestGenericClientHealthCheck.class);


	/**
	 * No mocking.. real instantiation
	 * 
	 * @throws GenericClientException
	 */

	private static  GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "JSON","TEST");
	Map<String, String> clientproperties;

	@Before
	public void setup()
	{
		clientConfiguration = clientConfiguration.buildCircuitBreaker("2","1000");
		try {
			clientproperties = clientConfiguration.createProperties();
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
	}


	@Test
	public void testCircuitBreakerSucessiveFailures()
	{
		StoreLocatorInfo storeInfo = null;
		Map<String,String> params=null;
		try {
			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/v1/597",
							params, StoreLocatorInfo.class,clientproperties);
		} catch (GenericClientException e) {
			LOGGER.error(e);
			LOGGER.debug(" 1st failure");
			try{
				storeInfo = ODGenericClientExecutor
						.clientExecutorInstance()
						.invokeRestTemplate(
								"http://svcdevtrunk.uschecomrnd.net/StoreLocatorService/stores/v1/597",
								params, StoreLocatorInfo.class,clientproperties);
			}
			catch(GenericClientException e1) {
				LOGGER.debug("it shoudl pass ");
			}
			try{
				storeInfo = ODGenericClientExecutor
						.clientExecutorInstance()
						.invokeRestTemplate(
								"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/v1/597",
								params, StoreLocatorInfo.class,clientproperties);
			}
			catch(GenericClientException e2) {
				LOGGER.debug(" 2nd failure");
				LOGGER.debug(" this should be allowed as the breaker should've been reset");
				try{
					storeInfo = ODGenericClientExecutor
							.clientExecutorInstance()
							.invokeRestTemplate(
									"http://svcdevtrunk.uschecomrnd.net/StoreLocatorService/stores/v1/597",
									params, StoreLocatorInfo.class,clientproperties);
					assertEquals("597",storeInfo.getStores()[0].getStoreId());
				}
				catch(GenericClientException e3) {
					LOGGER.debug(" Circuit breaker should not set");
				}
			 
				
			}
			
		}

	}




	@Test
	public void testInvokeRestTemplateHealthCheck()
			throws GenericClientException {
		StoreRouteInfo storeInfo = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("fromCity", "boca raton");
		params.put("fromState", "FL");
		params.put("toCity", "delray beach");
		params.put("toState", "FL");

		try { // shd fail wrong url
			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
							params, StoreRouteInfo.class,clientproperties);
			LOGGER.debug(" store route info " + storeInfo.toString());
		} catch (GenericClientException e) {

			try { // shd fail. wrong url

				storeInfo = ODGenericClientExecutor
						.clientExecutorInstance()
						.invokeRestTemplate(
								"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
								params, StoreRouteInfo.class,clientproperties);
			} catch (GenericClientException e1) {
				try { // should fail and service should be shutdown..

					storeInfo = ODGenericClientExecutor
							.clientExecutorInstance()
							.invokeRestTemplate(
									"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
									params, StoreRouteInfo.class,clientproperties);
					fail("should fail");
				} catch (GenericClientException g) {
					// ok to fail;
				}

			}

		}
	}

	/**
	 * 
	 * 
	 * @throws GenericClientException
	 */
	@Test
	public void testInvokeRestTemplateHealthCheckWithTimer()
			throws GenericClientException {
		StoreRouteInfo storeInfo = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("fromCity", "boca raton");
		params.put("fromState", "FL");
		params.put("toCity", "delay beach");
		params.put("toState", "FL");

		try {// shd fail. wrong url

			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
							params, StoreRouteInfo.class,clientproperties);

			LOGGER.debug(" store route info " + storeInfo.toString());
		} catch (GenericClientException e) {

			try {// shd fail. wrong url

				storeInfo = ODGenericClientExecutor
						.clientExecutorInstance()
						.invokeRestTemplate(
								"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
								params, StoreRouteInfo.class,clientproperties);
			} catch (GenericClientException e1) {
				try { // this should pull the trigger to shut the service

					storeInfo = ODGenericClientExecutor
							.clientExecutorInstance()
							.invokeRestTemplate(
									"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
									params, StoreRouteInfo.class,clientproperties);
				} catch (GenericClientException e2) {
					try {// shd not fail. wait and retry after some time

						LOGGER.debug("Take a Nap...ozzz!");
						Thread.sleep(12000); // to wait for service to get
						// restored
						LOGGER.debug("Got up .. Yee! Lets check");
						storeInfo = ODGenericClientExecutor
								.clientExecutorInstance()
								.invokeRestTemplate(
										"http://svcdevtrunk.uschecomrnd.net/StoreLocatorService/stores/directions",
										params, StoreRouteInfo.class,clientproperties);

					} catch (GenericClientException e3) {
						LOGGER.debug("Still Error??. Bad luck.");
					} catch (InterruptedException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					LOGGER.debug("Hurray!! Cool... works now..");
				}
			}
		}
		assertNotNull(storeInfo);
	}

	/**
	 * With Mocking
	 * 
	 * @throws GenericClientException
	 */

	@Test
	public void testInvokeRestTemplateHealthCheckWithTimerAndOtherService()
			throws GenericClientException {
		StoreRouteInfo storeInfo = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("fromCity", "boca raton");
		params.put("fromState", "FL");
		params.put("toCity", "delay beach");
		params.put("toState", "FL");

		try {// shd fail. wrong url

			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
							params, StoreRouteInfo.class,clientproperties);
			LOGGER.debug(" store route info " + storeInfo.toString());
		} catch (GenericClientException e) {
			// shd fail. wrong url
			try {

				storeInfo = ODGenericClientExecutor
						.clientExecutorInstance()
						.invokeRestTemplate(
								"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
								params, StoreRouteInfo.class,clientproperties);
			} catch (GenericClientException e1) {
				try { // this should pull the trigger to shut the service

					storeInfo = ODGenericClientExecutor
							.clientExecutorInstance()
							.invokeRestTemplate(
									"http://odservicesdev7.uschecomrnd.net:9084/StoreLocatorService/stores/directions",
									params, StoreRouteInfo.class,clientproperties);
				} catch (GenericClientException e2) {
					try {

						// call another service while  this  service is shutdown for maintanance
						testInvokeRestTemplateWithAnotherServiceDown(); 
						LOGGER.debug("Going for a Nap..");
						Thread.sleep(12000);// to wait for service to get restored
						LOGGER.debug("Wake up call??? Yee!!. Let me check the service now");
						storeInfo = ODGenericClientExecutor
								.clientExecutorInstance()
								.invokeRestTemplate(
										"http://svcdevtrunk.uschecomrnd.net/StoreLocatorService/stores/directions",
										params, StoreRouteInfo.class,clientproperties);

					} catch (GenericClientException e3) {
						LOGGER.debug(" Erroring out still??? Bad luck...");
					} catch (InterruptedException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					LOGGER.debug("Hurray!! Cool... works now..");
				}
			}
		}
		assertNotNull(storeInfo);

	}

	public void testInvokeRestTemplateWithAnotherServiceDown() {
		Map<String, String> params = null;
		StoreLocatorInfo storeInfo = null;
		LOGGER.debug("Lets try another service while this service is down");
		GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "JSON","TEST1");
		clientConfiguration = clientConfiguration.buildCircuitBreaker("3","1000");
		Map<String, String> clientproperties = null;
		try {
			clientproperties = clientConfiguration.createProperties();
			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://svcdevtrunk.uschecomrnd.net/StoreLocatorService/stores/v1/597",
							params, StoreLocatorInfo.class,clientproperties);
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
		LOGGER.debug("Store info " + storeInfo.toString());
		LOGGER.debug("mmmm...Other service looks to be good");

		assertNotNull(storeInfo);

	}

}
