package com.officedepot.service.generic.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;

public class TestGenericClientForBazaarVoice {

	private final static Logger LOGGER = Logger.getLogger(TestGenericClientForBazaarVoice.class);
	private static GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "TEXT","TEST");
	Map<String, String> clientProperties;
	@Before
	public void setup()
	{
		clientConfiguration = clientConfiguration.buildCircuitBreaker("2","1000");
		try {
			clientProperties = clientConfiguration.createProperties();
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
	}
	
	
	@Test
	public void testInvokeRestTemplateWithBazaarVoiceWithReviews() {
		String seo = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
		
			seo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://svcdevtrunk.uschecomrnd.net/products/315515/reviews/seo",
							params, String.class,clientProperties);
			LOGGER.debug(" seo " + seo.toString());
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
		assertNotNull(seo);

	}

	@Test
	public void testInvokeRestTemplateWithBazaarVoiceWithQnA() {
		String seo = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
		
			seo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://svcdevtrunk.uschecomrnd.net/products/315515/qna/seo",
							params, String.class,clientProperties);
			LOGGER.debug(" QndA " + seo.toString());
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
		assertNotNull(seo);

	}

 

}
