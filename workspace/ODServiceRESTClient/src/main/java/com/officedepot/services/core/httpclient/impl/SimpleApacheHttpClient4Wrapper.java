package com.officedepot.services.core.httpclient.impl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.log4j.Logger;

import com.officedepot.services.core.httpclient.HttpClientParameters;
import com.officedepot.services.core.httpclient.exceptions.HttpClientException;

public class SimpleApacheHttpClient4Wrapper extends AbstractApacheHttpClient4 {
		
	private final static Logger log = Logger.getLogger(SimpleApacheHttpClient4Wrapper.class);
	
	private final static int HTTPS_PORT = 443;
	private final static int HTTP_PORT = 80;
	
	private HttpClient httpClient;	
	
	public SimpleApacheHttpClient4Wrapper(HttpClientParameters parameters) {
		log.debug("Creating with " + parameters);
		ThreadSafeClientConnManager ccm = new NoWaitThreadSafeClientConnManager(createDummySchemeRegistry());
		NoWaitThreadSafeClientConnManager.setMaxConnections(ccm, parameters);
		httpClient = new DefaultHttpClient(ccm);
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), parameters.getConnectionTimeoutMillis());
		HttpConnectionParams.setSoTimeout(httpClient.getParams(), parameters.getSocketTimeoutMillis());		
	}
	
	@Override
	protected HttpClient getHttpClient() {
		return httpClient;
	}
	
	private SchemeRegistry createDummySchemeRegistry() {
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
		}
		catch (NoSuchAlgorithmException e) {
			throw new HttpClientException(e.getMessage());
		}
		try {
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
	            public X509Certificate[] getAcceptedIssuers() {
	            	log.warn("Accepted Issuers bypassed");
	                return null;
	            }

	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	            	log.warn("Check client trusted bypassed");
	            }

	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	            	log.warn("Check server trusted bypassed");
	            }
			} }, new SecureRandom());
		}
		catch (KeyManagementException e) {
			throw new HttpClientException(e.getMessage());
		}
		SSLSocketFactory sf = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme httpsScheme = new Scheme("https", HTTPS_PORT, sf);
		Scheme httpScheme = new Scheme("http", HTTP_PORT, PlainSocketFactory.getSocketFactory());
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(httpsScheme);
		schemeRegistry.register(httpScheme);
		return schemeRegistry;
	}
}