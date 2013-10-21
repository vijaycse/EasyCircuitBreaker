package com.officedepot.service.generic.client.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import com.officedepot.service.generic.client.impl.GenericRestWebResource;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class TestBasicAuthentication {

	@Test
	public void testClientConfiguratorUtilityWithBasicAuthentication() throws GenericClientException {
		final String usr = "usr";
		final String pwd = "pwd";
		GenericClientConfiguratorUtility instance = new GenericClientConfiguratorUtility("POST", "JSON", "foo");
		instance.buildBasicAuthentication(usr, pwd);
		Map<String, String> properties = instance.createProperties();
		assertEquals(usr, properties.get("basicAuthenticationUsername"));
		assertEquals(pwd, properties.get("basicAuthenticationPassword"));		
	}
	
	@Test
	public void testClientConfiguratorUtilityNoBasicAuthentication() throws GenericClientException {
		GenericClientConfiguratorUtility instance = new GenericClientConfiguratorUtility("POST", "JSON", "foo");
		Map<String, String> properties = instance.createProperties();
		assertNull(properties.get("basicAuthenticationUsername"));
		assertNull(properties.get("basicAuthenticationPassword"));		
	}
		
	@Test
	public void testHTTPBasicAuthFilterSetup() throws GenericClientException {
		final String usr = "usr";
		final String pwd = "pwd";
		Client client = new Client();
		WebResource webResource = client.resource("");
		GenericRestWebResource.WEBRESOURCE_INSTANCE.setClientFilters(webResource, usr, pwd,new HashMap<String, List<Object>>());
		Object head = (Object) Whitebox.getInternalState(webResource, "head");
		assertTrue(head instanceof HTTPBasicAuthFilter);
	}
	
	@Test
	public void testHTTPBasicAuthFilterNotSetup() throws GenericClientException {
		Client client = new Client();
		WebResource webResource = client.resource("");
		GenericRestWebResource.WEBRESOURCE_INSTANCE.setClientFilters(webResource, "", "",new HashMap<String, List<Object>>());
		Object head = (Object) Whitebox.getInternalState(webResource, "head");
		assertFalse(head instanceof HTTPBasicAuthFilter);
	}
}
