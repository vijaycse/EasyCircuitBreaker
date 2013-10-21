package com.officedepot.service.generic.client.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;
import com.officedepot.services.storelocator.model.StoreLocatorInfo;

public class TestGenericClientForStoreLocator {

	private final static Logger LOGGER = Logger
			.getLogger(TestGenericClientForStoreLocator.class);

	private final static  GenericClientConfiguratorUtility CLIENT_CONFIGURATION = new GenericClientConfiguratorUtility("GET", "JSON","StoreLocator");
	 

	@Test
	public void testInvokeRestTemplateWithStoreLocatorCode() {
		Map<String, String> params = null;
		StoreLocatorInfo storeInfo = null;
		try {
			Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://svcdevtrunk.uschecomrnd.net/StoreLocatorService/stores/v1/597",
							params, StoreLocatorInfo.class,clientConfigurator);
		} catch (GenericClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.debug(" store info " + storeInfo.toString());
		assertEquals("597",storeInfo.getStores()[0].getStoreId());
		//assertNotNull(storeInfo);
	}
	
	
	@Test
	public void testInvokeRestTemplateWithStoreLocatorAddress() {
		Map<String, String> params =  new HashMap<String, String>();
		StoreLocatorInfo storeInfo = null;
		try {
			params.put("city", "boca raton");
			params.put("state", "Florida");
			Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://svcdevtrunk.uschecomrnd.net/StoreLocatorService/stores/",
							params, StoreLocatorInfo.class,clientConfigurator);
		} catch (GenericClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.debug(" store info " + storeInfo.toString());
		//assertNotNull(storeInfo);
		assertEquals("BOCA RATON",storeInfo.getStores()[0].getCity());
	}
	

}
