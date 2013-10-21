package com.officedepot.service.generic.client.load.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;

public class TestGenericClientForBazaarVoicePerf {

	private final static Logger LOGGER = Logger.getLogger(TestGenericClientForBazaarVoicePerf.class);
	private GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "TEXT","BAZZARVOICE");
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
	public void testInvokeRestTemplateWithBazaarVoiceWithReviews() {
		String seo = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
		
			seo = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesperf.uschecomrnd.net:6150/products/315515/reviews/seo",
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
							"http://odservicesperf.uschecomrnd.net:6150/products/315515/qna/seo",
							params, String.class,clientProperties);
			LOGGER.debug(" QndA " + seo.toString());
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
		assertNotNull(seo);

	}

 

}
