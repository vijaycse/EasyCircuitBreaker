package com.officedepot.service.generic.client.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;

import com.officedepot.service.generic.client.model.GenericClientException;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class GenericClientUtility {

	private GenericClientUtility() {
	}

	private final static Logger LOGGER = Logger
			.getLogger(GenericClientUtility.class);
	private static final String TIMESTAMP_FORMATTER = "yyyy-MM-dd-HH.mm.ss.SSSSSS";

	/**
	 * Converting JSON to POJO
	 * 
	 * @param rawStoreLocatorInfo
	 * @param pojo
	 * @return
	 * @throws GenericClientException
	 */
	public static <T> T convertJSONtoPOJO(final String rawEntityResponse,
			final Class<T> pojo) throws GenericClientException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T anyType = null;
		try {
			anyType = mapper.readValue(rawEntityResponse, pojo);
		} catch (JsonParseException e) {
			LOGGER.error(e);
			throw new GenericClientException(" JsonParseException ",
					e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e);
			throw new GenericClientException(" JsonParseException ",
					e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e);
			throw new GenericClientException(" IOException ", e.getMessage());
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new GenericClientException(" Exception - JSON ",
					ex.getMessage());
		}
		return anyType;
	}

	/**
	 * 
	 * @param <T>
	 * @param rawData
	 * @return
	 * @throws GenericClientException
	 */
	public static <T> T convertTexttoGeneric(final String rawData)
			throws GenericClientException {
		T anyType = null;
		anyType = transform(rawData);
		return anyType;
	}

	@SuppressWarnings("unchecked")
	private static <T> T transform(final String rawData) {
		return (T) rawData;
	}

	/**
	 * <p>
	 * Converts general Map to jersey based javax.ws.rs.core.MultivaluedMap.
	 * 
	 * Note:I do not see a situation where we need to really use multivalued
	 * map.. and hence,letting the users to make use of Util.Map and pass all
	 * the parameters and here converting the util.map to
	 * javax.ws.rs.core.MultivaluedMap
	 * </p>
	 * 
	 * @param params
	 * @return
	 * @throws GenericClientException
	 */

	public static MultivaluedMap<String, String> convertMaptoMultiValuedMap(
			final Map<String, String> params) throws GenericClientException {
		MultivaluedMap<String, String> restParam = new MultivaluedMapImpl();
		if (params != null) {
			for (Map.Entry<String, String> e : params.entrySet()) {
				restParam.add(e.getKey(), e.getValue());
			}
		} else {
			LOGGER.error("Parameters are invalid ");
			throw new GenericClientException(" 1001 ", "Parameters are Invalid");
		}
		return restParam;

	}

	/**
	 * DateFormat is not thread safe and hence making it local for every call.
	 * Making it synchronized is not efficient with this context.
	 * 
	 */

	public static SimpleDateFormat dateFormatter() {
		return new SimpleDateFormat(TIMESTAMP_FORMATTER);
	}
	
	
	/**
	 * 
	 * @param entrySet
	 * @return
	 */
	public static NewCookie convertEntrySetToCookie(Map.Entry<String,String> entrySet){
		return new NewCookie(new Cookie(entrySet.getKey(), entrySet.getValue()));
	}
	
}
