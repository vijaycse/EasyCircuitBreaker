package com.officedepot.service.generic.client.test;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;
import com.officedepot.services.storelocator.model.StoreRouteInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TestGenericClientForNonFunctional {

	private final static Logger LOGGER = Logger
			.getLogger(TestGenericClientForNonFunctional.class);
	
	private final static  GenericClientConfiguratorUtility CLIENT_CONFIGURATION = new GenericClientConfiguratorUtility("GET", "JSON","TEST");
	
	@Test
	public void testHandleWebResourceTimeoutException() {
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		Client client = Client.create();
		WebResource resource = client
				.resource(
						"http://odservicesdev8.uschecomrnd.net:9084/StoreLocatorService/stores/v1/598")
				.queryParams(params);
		resource.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT,
				0001);
		resource.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, 0001);
		try {
			resource.get(String.class);
			fail("Should have thrown TimeoutException");
		} catch (Exception ex) {
			// fail(ex.getMessage());
		}
	}

	@Test
	public void testHTTP404Status() {
		StoreRouteInfo storeInfo = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
			params.put("fromCity", "boca raton");
			params.put("fromState", "FL");
			params.put("toCity", "delay beach");
			params.put("toState", "FL");
			storeInfo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesdev8.uschecomrnd.net:9084/StoreLocatorService/stores/direction",
							params, StoreRouteInfo.class,clientConfigurator);
			LOGGER.debug(" store route info " + storeInfo.toString());
			fail(" it should throw executionexception coz' the uri  path is mispelled");
		} catch (GenericClientException e) {
			// ok
		}

	}

	@Test
	public void testHTTP500Status() {
		WebResource service = Client
				.create()
				.resource(
						"http://odservicesdev8.uschecomrnd.net:9084/StoreLocatorService/stores/v1/");
		WebResource serviceSpy = Mockito.spy(service);
		Mockito.doThrow(new RuntimeException("500!")).when(serviceSpy)
				.get(String.class);
		try {
			serviceSpy.get(String.class);
			fail(" should fail with HTTP 500");
		} catch (Exception e) {
			// ok
		}
	}

}
