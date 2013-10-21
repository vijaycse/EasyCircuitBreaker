package com.officedepot.service.generic.client.load.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;
import com.officedepot.services.storelocator.model.StoreLocatorInfo;

public class TestGenericClientForStoreLocatorPerf {

	private final static Logger LOGGER = Logger
			.getLogger(TestGenericClientForStoreLocatorPerf.class);

	private GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "JSON","StoreLocator");
	Map<String, String> clientProperties;

	@Before
	public void setup()
	{
		clientConfiguration = clientConfiguration.buildCircuitBreaker("5","60000");
		clientConfiguration = clientConfiguration.buildConnectionPool("20", "8000");
		clientConfiguration = clientConfiguration.buildThreadPool("2", "10", "1000", "20");
		try {
			clientProperties = clientConfiguration.createProperties();
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
	}


	@Test
	public void testInvokeRestTemplateWithStoreLocatorCode() {
		Map<String, String> params = null;
		StoreLocatorInfo storeInfo = null;
		try {
			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesperf.uschecomrnd.net:6152/StoreLocatorService/stores/v1/597",
							params, StoreLocatorInfo.class,clientProperties);
			LOGGER.debug(" store info " + storeInfo.toString());
			assertEquals("597",storeInfo.getStores()[0].getStoreId());
			//assertNotNull(storeInfo);
		} catch (GenericClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Test
	public void testInvokeRestTemplateWithStoreLocatorAddress() {
		Map<String, String> params =  new HashMap<String, String>();
		StoreLocatorInfo storeInfo = null;
		try {
			params.put("city", "boca raton");
			params.put("state", "Florida");

			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesperf.uschecomrnd.net:6152/StoreLocatorService/stores",
							params, StoreLocatorInfo.class,clientProperties);
			LOGGER.debug(" store info " + storeInfo.toString());
			assertEquals("BOCA RATON",storeInfo.getStores()[0].getCity());
			//assertNotNull(storeInfo);
		} catch (GenericClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
