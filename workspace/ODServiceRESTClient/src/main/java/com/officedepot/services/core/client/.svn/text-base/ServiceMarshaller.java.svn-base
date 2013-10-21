package com.officedepot.services.core.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Category;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.officedepot.type.EComContext;

public class ServiceMarshaller<T> {
	private static final Category logger =  Category.getInstance(ServiceMarshaller.class.getName());
	
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		//mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public T invoke(Class<T> clazz, String location,EComContext eComContext) throws ServiceClientException{
		if(eComContext != null){
			location = location + (location.indexOf('?') > -1? "&" : "?") + "ecom=" + eComContext.calcKey();
		}
		String json = getHttpContent(location);
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			throw new ServiceClientSerializationException(e.getMessage(), e);			
		} catch (JsonMappingException e) {
			throw new ServiceClientSerializationException(e.getMessage(), e);
		} catch (IOException e) {
			throw new ServiceClientCommunicationException(e.getMessage(), e);
		}		
	}
	
	//TODO: Token support
	public static String getHttpContent(String url) throws ServiceClientCommunicationException {
		StringBuffer sb = new StringBuffer();
		HttpClient client = new DefaultHttpClient();
		HttpGet method = new HttpGet(url);

		try {
			HttpResponse response = client.execute(method);			
			HttpEntity entity = response.getEntity();
			if (entity!=null){				 
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {				
					sb.append(EntityUtils.toString(entity));
				} else {
					logger.error("Http call failed: " + response.getStatusLine().getStatusCode());
				}		
				entity.getContent().close();
			}			
		} catch (ClientProtocolException e) {
			method.abort();
			logger.error("getHttpContent failed: " + e.getMessage(), e);
			throw new ServiceClientCommunicationException(e.getMessage(),e);
		} catch (IOException e) {
			method.abort();
			logger.error("getHttpContent failed: " + e.getMessage(), e);
			throw new ServiceClientCommunicationException(e.getMessage(),e);
		}
		return sb.toString();
	}
}
