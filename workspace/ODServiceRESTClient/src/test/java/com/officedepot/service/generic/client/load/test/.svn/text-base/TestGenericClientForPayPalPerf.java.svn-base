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
import com.officedepot.services.paypal.model.PayPalAccountDetails;
import com.officedepot.services.paypal.model.PayPalToken;

public class TestGenericClientForPayPalPerf {

	private final static Logger LOGGER = Logger
			.getLogger(TestGenericClientForPayPalPerf.class);

	/***********************************************************************
	 * disclaimer : THIS TEST CASE DO NOT MEAN TO TEST PAYPAL BUT GENERIC
	 * CLIENT. IT TESTS WHETHER IT MAKES SUCCESSFUL CONNECTION TO PAYPAL AND
	 * RETURNS CORRECT RESPONSE, DOES NOT VALIDATE THE RESPONSE.
	 ************************************************************************/
	private GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("POST", "JSON","paypal");
	Map<String, String> clientProperties;
	
	@Before
	public void setup()
	{
		clientConfiguration = clientConfiguration.buildCircuitBreaker("5","60000");
		clientConfiguration = clientConfiguration.buildConnectionPool("20", "20000");
		clientConfiguration = clientConfiguration.buildThreadPool("2", "10", "1000", "20");
		try {
			clientProperties = clientConfiguration.createProperties();
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
	}
	
	
	@Test
	public void testInvokeRestTemplateWithPayPalInitSession() {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put(
				"payload",
				"{\"payPalOrder\":{\"totalAmount\":11.12,\"currencyCode\":\"USD\"},\"shippingAddress\":null,\"cancelUrl\":\"https://localhost:443/cart/shoppingCart.do\",\"returnUrl\":\"https://localhost:443/checkout/paypalReturn.do?anonymousMode=true&ignoreShipping=false\"}");
		try {
			PayPalToken token = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesperf.uschecomrnd.net:6151/paypal/initSession",
							params, PayPalToken.class,clientProperties);
			LOGGER.debug("result  token " + token);
			assertNotNull(token);
			callPayPalAccountDetails(token);
		} catch (Exception e) {

		}

	}

	/**
	 * This always returns no values with json object .. so no issues as far as
	 * generic client test case goes
	 * 
	 * @param token
	 */

	private void callPayPalAccountDetails(PayPalToken token) {
		Map<String, String> params = new HashMap<String, String>();
		
		String tokenJson = token.toJson();
		params.put("payload", tokenJson);
		try {
			PayPalAccountDetails account = ODGenericClientExecutor
					.clientExecutorInstance()
					.invokeRestTemplate(
							"http://odservicesperf.uschecomrnd.net:6151/paypal/getAccountDetails",
							params, PayPalAccountDetails.class,clientProperties);
			LOGGER.debug("result account " + account);
			assertNotNull(account);
			//placePayPalOrder(token, account);
		} catch (GenericClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
