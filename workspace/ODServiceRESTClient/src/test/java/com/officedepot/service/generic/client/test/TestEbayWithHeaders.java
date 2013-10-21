package com.officedepot.service.generic.client.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.officedepot.service.generic.client.impl.ODGenericClientExecutor;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;

public class TestEbayWithHeaders {

	//private final static  GenericClientConfiguratorUtility CLIENT_CONFIGURATION = new GenericClientConfiguratorUtility("GET", "JSON","EbayAPITest");

	
	/**Chose Ebay findKeyword API to validate the headers.
	 * The first Method calls the findKeyword API without any headers but all params
	 * The second one calls the same API with no params but all headers
	 *  
	 */
	
	private String paramsNoheadersTestResult;
	private String headersNoParamsTestResult;
	 
	 /***
	  * 
	  * @return 
	 * @throws GenericClientException
	  */
	
	//@Test
	public void testinvokeRestTemplateWithRequestHeaders() throws GenericClientException{
		testinvokeRestTemplateWithRequestHeadersButNoHeaders();
		testinvokeRestTemplateWithRequestHeadersWithActualHeaders();
		testCompareTwoTestsResults();
	}
	
	 @Test
	public void testinvokeRestTemplateWithRequestHeadersButNoHeaders() throws GenericClientException{
		//String BASE_URI=  "http://svcs.sandbox.ebay.com/services/search/FindingService/v1";
		 GenericClientConfiguratorUtility CLIENT_CONFIGURATION = new GenericClientConfiguratorUtility("GET", "JSON","EbayAPITest");
		 String BASE_URI="http://svcs.ebay.com/services/search/FindingService/v1";
		Map<String, String> params =  new HashMap<String, String>();
		params.put("OPERATION-NAME", "getHistograms");
		params.put("SERVICE-NAME", "FindingService");
		params.put("SERVICE-VERSION", "1.0.0");
		params.put("GLOBAL-ID", "EBAY-US");
		params.put("SECURITY-APPNAME", "odf910a35-c12d-4281-bc4d-928c86fd58a");
		params.put("RESPONSE-DATA-FORMAT", "JSON");
		params.put("REST-PAYLOAD", "");
		params.put("categoryId", "11233");
		Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
		Map<String,List<Object>> headers = new HashMap<String, List<Object>>();
		paramsNoheadersTestResult = ODGenericClientExecutor.clientExecutorInstance().invokeRestTemplateWithRequestHeaders(BASE_URI, params , String.class, clientConfigurator, headers,new HashMap<String,String>());
		System.out.println(" paramsNoheadersTestResult  " + paramsNoheadersTestResult);
		assertNotNull(paramsNoheadersTestResult);
		
		}
	
	 @Test
	public void testinvokeRestTemplateWithRequestHeadersWithActualHeaders() throws GenericClientException{
		//String BASE_URI=  "http://svcs.sandbox.ebay.com/services/search/FindingService/v1";
		 GenericClientConfiguratorUtility CLIENT_CONFIGURATION = new GenericClientConfiguratorUtility("GET", "JSON","EbayAPITest");
		 String BASE_URI="http://svcs.ebay.com/services/search/FindingService/v1/";
		Map<String, String> params =  new HashMap<String, String>();
		params.put("REST-PAYLOAD", "");
		params.put("categoryId", "11233");
		Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
		
		Map<String,List<Object>> headers = new HashMap<String, List<Object>>();
		
		List<Object> value1 = new ArrayList<Object>();
		value1.add("getHistograms");
		headers.put("X-EBAY-SOA-OPERATION-NAME", value1);
		
		List<Object> valueX = new ArrayList<Object>();
		valueX.add("FindingService");
		headers.put("X-EBAY-SOA-SERVICE-NAME", valueX);
		
		List<Object> value2 = new ArrayList<Object>();
		value2.add("1.0.0");
		headers.put("X-EBAY-SOA-SERVICE-VERSION", value2);
	
		List<Object> value3 = new ArrayList<Object>();
		value3.add("EBAY-US");
		headers.put("X-EBAY-SOA-GLOBAL-ID", value3);
		 
		List<Object> value4 = new ArrayList<Object>();
		//value4.add("odf910a35-c12d-4281-bc4d-928c86fd58a");
		value4.add("od4d1b263-d46a-4afc-b8c4-47e8a7f6384");
		
		headers.put("X-EBAY-SOA-SECURITY-APPNAME", value4);
		
		List<Object> value6 = new ArrayList<Object>();
		value6.add("JSON");
		headers.put("X-EBAY-SOA-REQUEST-DATA-FORMAT", value6);
		
		List<Object> value7 = new ArrayList<Object>();
		value7.add("");
		headers.put("REST-PAYLOAD",value7);
		
		List<Object> value12 = new ArrayList<Object>();
		value12.add("11233");
 		headers.put("categoryId", value12);
 	 

 		headersNoParamsTestResult = ODGenericClientExecutor.clientExecutorInstance().invokeRestTemplateWithRequestHeaders(BASE_URI, params , String.class, clientConfigurator, headers,new HashMap<String,String>());
 		System.out.println(" paramsheadersTestResult  " + paramsNoheadersTestResult);
		assertNotNull(headersNoParamsTestResult);
	}
	
 
	public void testCompareTwoTestsResults() throws GenericClientException{
		Assert.assertEquals(headersNoParamsTestResult, paramsNoheadersTestResult);
	}

	@Test
	public void testebayTime() throws GenericClientException{
		String BASE_URI="http://open.api.ebay.com/shopping?";
		Map<String, String> params =  new HashMap<String, String>();
		GenericClientConfiguratorUtility CLIENT_CONFIGURATION = new GenericClientConfiguratorUtility("GET", "JSON","EbayAPITest");
		CLIENT_CONFIGURATION.buildConnectionPool("10", "10000");
		Map<String, String> clientConfigurator = CLIENT_CONFIGURATION.createProperties();
		
		Map<String,List<Object>> headers = new HashMap<String, List<Object>>();
		
		List<Object> value1 = new ArrayList<Object>();
		value1.add("829");
		headers.put("X-EBAY-API-VERSION", value1);
		
		List<Object> valueX = new ArrayList<Object>();
		valueX.add("0");
		headers.put("X-EBAY-API-SITE-ID", valueX);
		
		List<Object> value2 = new ArrayList<Object>();
		value2.add("GeteBayOfficialTime");
		headers.put("X-EBAY-API-CALL-NAME", value2);
		
		/*List<Object> value3 = new ArrayList<Object>();
		value3.add("580b0dfe-21c0-4d63-aed2-911ddbafb8fc");
		headers.put("X-EBAY-API-DEV-NAME", value3);*/
		
		/*List<Object> value5 = new ArrayList<Object>();
		value3.add("c84b649f-3bf5-41da-acb1-20367aac8e1f");
		headers.put("X-EBAY-API-CERT-NAME", value5);*/
		
		List<Object> value4 = new ArrayList<Object>();
		value4.add("odf910a35-c12d-4281-bc4d-928c86fd58a");
		headers.put("X-EBAY-API-APP-ID", value4);
		
		List<Object> value6 = new ArrayList<Object>();
		value6.add("JSON");
		headers.put("X-EBAY-API-REQUEST-ENCODING", value6);
		
		headersNoParamsTestResult = ODGenericClientExecutor.clientExecutorInstance().invokeRestTemplateWithRequestHeaders(BASE_URI, params , String.class, clientConfigurator, headers,new HashMap<String,String>());
 		System.out.println(" paramsNoheadersTestResult  " + paramsNoheadersTestResult);
		assertNotNull(headersNoParamsTestResult);
	
		
	}
}
