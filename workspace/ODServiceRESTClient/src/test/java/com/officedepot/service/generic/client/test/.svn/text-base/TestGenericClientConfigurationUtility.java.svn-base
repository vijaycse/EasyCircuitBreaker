package com.officedepot.service.generic.client.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.util.GenericClientConfiguratorUtility;

public class TestGenericClientConfigurationUtility {

	private final static Logger LOGGER = Logger
			.getLogger(TestGenericClientConfigurationUtility.class);

	@Test
	public void testConfigurationCreator() {

		Map<String, String> clientConfigurator = null;
		GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "JSON","TEST");
		clientConfiguration = clientConfiguration.buildThreadPool("1", "10","1000","100");
		clientConfiguration = clientConfiguration.buildConnectionPool("20","8000");
		clientConfiguration = clientConfiguration.buildCircuitBreaker("3","1000");
		try {
			clientConfigurator = clientConfiguration.createProperties();
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
		LOGGER.debug(" result " + clientConfigurator.toString());
		assertNotNull(clientConfigurator);
	}

	@Test
	public void testConfigurationCreatorWithInvalidRepresentation() {

		GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "XML","TEST");
		clientConfiguration = clientConfiguration.buildThreadPool("1", "10","1000","100");
		clientConfiguration = clientConfiguration.buildConnectionPool("20","8000");
		clientConfiguration = clientConfiguration.buildCircuitBreaker("5","6000");
		try {
			clientConfiguration.createProperties();
			fail(" shoudl fail");
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}

	}

	@Test
	public void testConfigurationCreatorWithInvalidConnectionPool() {
		Map<String, String> clientConfigurator = null;
		GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility("GET", "JSON","TEST");
		clientConfiguration = clientConfiguration.buildThreadPool("1", "10","1000","100");
		clientConfiguration = clientConfiguration.buildConnectionPool("c","AB");
		clientConfiguration = clientConfiguration.buildCircuitBreaker("5","6000");
		try {
			clientConfigurator = clientConfiguration.createProperties();
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}
		LOGGER.debug(" result " + clientConfigurator.toString());
		assertNotNull(clientConfigurator);

	}

	@Test
	public void testConfigurationWithNullCheck() {
		GenericClientConfiguratorUtility clientConfiguration = new GenericClientConfiguratorUtility(null, null,"TEST");
		clientConfiguration = clientConfiguration.buildThreadPool("1", "10","1000","100");
		clientConfiguration = clientConfiguration.buildConnectionPool("20","8000");
		try {
			clientConfiguration.createProperties();
			fail(" should fail");
		} catch (GenericClientException e) {
			LOGGER.error(e);
		}

	}

}
