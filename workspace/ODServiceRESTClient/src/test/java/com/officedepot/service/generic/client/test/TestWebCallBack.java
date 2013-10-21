package com.officedepot.service.generic.client.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.impl.ODServiceRESTClientResponse;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;

public class TestWebCallBack {

	private final static Logger LOGGER = Logger
			.getLogger(TestWebCallBack.class);
	private final static GenericClientConfiguratorUtility CLIENT_CONFIGURATION = new GenericClientConfiguratorUtility(
			"POST", "FORMURLENCODED", "webcallback");

	@Test
	public void testInvokeRestTemplateWithWebCallBack() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ep", "136");
		params.put("eid", "3");
		params.put("now", "true");
		params.put("dn", "15614388367");
		params.put("at", "136");
		ODServiceRESTClientResponse response = null;
		try {
			Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
			response = ODGenericClientExecutor.clientExecutorInstance().invokeRestTemplateReturnsEntityResponse(
							"http://wcb-sbxa.transerainc.net/wcb/webcallback",
							params, 
							clientConfigurator);
			LOGGER.debug("result  token " + response);

		} catch (Exception e) {
			LOGGER.error(" error" + e);
		}
		assertNotNull(response);
		assertEquals(200, response.getResponseHttpStatus());
		String str = response.getResponseEntity(String.class);
		LOGGER.debug(" response " + str);
	}
	
	
	@Test
	public void testInvokeRestTemplateWithWebCallBackWithHeaders() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ep", "136");
		params.put("eid", "3");
		params.put("now", "true");
		params.put("dn", "15614388367");
		params.put("at", "136");
		ODServiceRESTClientResponse response = null;
		try {
			Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
			response = ODGenericClientExecutor.clientExecutorInstance().invokeRestTemplateReturnsEntityResponseWithRequestHeaders("http://wcb-sbxa.transerainc.net/wcb/webcallback", params, 
					clientConfigurator, new HashMap<String, List<Object>>(),new HashMap<String,String>());
							 
			LOGGER.debug("result  token " + response);

		} catch (Exception e) {
			LOGGER.error(" error" + e);
		}
		assertNotNull(response);
		assertEquals(200, response.getResponseHttpStatus());
		String str = response.getResponseEntity(String.class);
		LOGGER.debug(" response " + str);
	}
	
}
