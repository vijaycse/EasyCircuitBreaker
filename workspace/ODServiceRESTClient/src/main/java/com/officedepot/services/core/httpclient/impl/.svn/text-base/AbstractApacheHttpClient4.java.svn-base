package com.officedepot.services.core.httpclient.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import com.officedepot.services.core.httpclient.HttpClientWrapper;
import com.officedepot.services.core.httpclient.HttpClientWrapperResponse;
import com.officedepot.services.core.httpclient.exceptions.HttpClientBadStatusCodeException;
import com.officedepot.services.core.httpclient.exceptions.HttpClientException;
import com.officedepot.services.core.httpclient.exceptions.HttpClientTimeoutException;

abstract public class AbstractApacheHttpClient4 implements HttpClientWrapper {
		
	private final static Logger log = Logger.getLogger(AbstractApacheHttpClient4.class);
	
	private final static Set<Cookie> NO_COOKIE = new HashSet<Cookie>();
	private final static List<NameValuePair> NO_PARAMETER = new ArrayList<NameValuePair>();
	private final static List<NameValuePair> NO_HEADER = new ArrayList<NameValuePair>();
	
	abstract protected HttpClient getHttpClient();
	
	// Without cookies
	
	public HttpClientWrapperResponse get(String uri) {
		return get(uri, NO_COOKIE);
	}
	
	public HttpClientWrapperResponse getJson(String uri) {
		return get(uri, NO_COOKIE);
	}

	public HttpClientWrapperResponse postJson(String uri, String payload) {
		return postJson(uri, payload, NO_COOKIE);
	}
	
	public HttpClientWrapperResponse postNvp(String uri, List<NameValuePair> nameValuePairs) {
		return postNvp(uri, nameValuePairs, NO_COOKIE);
	}
	
	public HttpClientWrapperResponse post(String uri, List<NameValuePair> headers) {
		return post(uri, headers, NO_COOKIE);
	}	
	
	// With cookies
	
	public HttpClientWrapperResponse get(String uri, Set<Cookie> cookies) {
		return executeHttpUriRequest(createHttpGet(uri), createHttpContext(cookies));
		
	}
	
	public HttpClientWrapperResponse getJson(String uri, Set<Cookie> cookies) {
		return executeHttpUriRequest(createHttpGetJson(uri), createHttpContext(cookies));
		
	}

	public HttpClientWrapperResponse postJson(String uri, String payload, Set<Cookie> cookies) {
		return executeHttpUriRequest(createHttpPost(uri, payload), createHttpContext(cookies));
	}
	
	public HttpClientWrapperResponse postNvp(String uri, List<NameValuePair> nameValuePairs, Set<Cookie> cookies) {
		return executeHttpUriRequest(createHttpPost(uri, nameValuePairs, NO_HEADER), createHttpContext(cookies));
	}
	
	public HttpClientWrapperResponse post(String uri, List<NameValuePair> headers, Set<Cookie> cookies) {
		return executeHttpUriRequest(createHttpPost(uri, NO_PARAMETER, headers), createHttpContext(cookies));
	}	
	
	// Private
 	
	private HttpGet createHttpGet(String uri) {
		return new HttpGet(uri);
	}	
	
	/*
	 * Added to support Method Get and content JSON
	 */
	
	private HttpGet createHttpGetJson(String uri) {
		HttpGet result = new HttpGet(uri);
		result.setHeader("Accept", "application/json");
		return result;
	}	
	
	
	private HttpPost createHttpPost(String fullUrl, String payload) {
		HttpPost result = new HttpPost(fullUrl);
		result.setHeader("Content-type", "application/json");
		try {
			result.setEntity(new StringEntity(payload));
		}
		catch (UnsupportedEncodingException ex) {
			final String errorMsg = "UnsupportedEncodingException: " + ex.getMessage();
			log.error(errorMsg);			
			throw new HttpClientException(errorMsg);
		}
		return result;
	}
	
	private HttpPost createHttpPost(String uri, List<NameValuePair> parameters, List<NameValuePair> headers) {
		HttpPost result = new HttpPost(uri);
		for (NameValuePair header : headers) {
			result.addHeader(header.getName(), header.getValue());
		}
		try {
			result.setEntity(new UrlEncodedFormEntity(parameters));
		}
		catch (UnsupportedEncodingException ex) {
			final String errorMsg = "UnsupportedEncodingException: " + ex.getMessage();
			log.error(errorMsg);			
			throw new HttpClientException(errorMsg);
		}
		return result;
	}

	private HttpClientWrapperResponse executeHttpUriRequest(HttpUriRequest request, HttpContext httpContext) {
		log.debug("Executing HTTP URI request");
		InputStream is = null;
		try {			
			HttpResponse httpResponse = getHttpClient().execute(request, httpContext);
			checkHttpResponse(httpResponse);
			final int statusCode = httpResponse.getStatusLine().getStatusCode();		
			is = httpResponse.getEntity().getContent();
			final String data = toString(is);
			log.debug(data);
			if (HttpStatus.SC_OK != statusCode) {
				final String errorMsg = "Http result code is " + statusCode;
				log.error(errorMsg);
				// It makes it easy to customize exception handling by subclassing
				// A subclass can catch this exception and, for example, parse
				// "result" as JSON
				throw new HttpClientBadStatusCodeException(errorMsg, statusCode, data);
			}
			Set<Cookie> cookies = retrieveCookies((BasicCookieStore) httpContext.getAttribute(ClientContext.COOKIE_STORE));
			HttpClientWrapperResponse result = new HttpClientWrapperResponseImpl(
					data,
					cookies
			);
			log.debug("HTTP method execution returned " + result);
			return result;
		}
		catch (ConnectTimeoutException ex) {
			final String errorMsg = "ConnectTimeoutException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientTimeoutException(errorMsg);
		}
		catch (SocketTimeoutException ex) {
			final String errorMsg = "SocketTimeoutException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientTimeoutException(errorMsg);
		}		
		catch (ClientProtocolException ex) {
			final String errorMsg = "ClientProtocolException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);			
		}		
		catch (IOException ex) {
			final String errorMsg = "IOException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);				
		}
		finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	private static Set<Cookie> retrieveCookies(BasicCookieStore basicCookieStore) {
		return new HashSet<Cookie>(basicCookieStore.getCookies());
	}

	private static HttpContext createHttpContext(Set<Cookie> cookies) {
		HttpContext result = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		result.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		for (Cookie cookie : cookies) {
			cookieStore.addCookie(cookie);
		}
		return result;
	}	
	
	private static void checkHttpResponse(HttpResponse httpResponse) throws IOException {
		if (null == httpResponse) {
			throw new HttpClientException("HTTP response is null");
		}
		if (null == httpResponse.getStatusLine()) {
			throw new HttpClientException("Status line is null");
		}
		if (null == httpResponse.getEntity()) {
			throw new HttpClientException("Entity is null");				
		}
		if (null == httpResponse.getEntity().getContent()) {
			throw new HttpClientException("Entity content is null");
		}
	}
	
	private static String toString(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is, writer, "UTF-8");
		return writer.toString();
	}
}