package com.officedepot.services.core.httpclient.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;

import com.officedepot.services.core.httpclient.HttpClientParameters;
import com.officedepot.services.core.httpclient.HttpClientWrapper;
import com.officedepot.services.core.httpclient.exceptions.HttpClientException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

public class CertificateEnabledApacheHttpClient4Wrapper extends AbstractApacheHttpClient4 implements HttpClientWrapper {
		
	private final static Logger log = Logger.getLogger(CertificateEnabledApacheHttpClient4Wrapper.class);
	
	private static final String SECURE_RANDOM_TYPE = "SHA1PRNG";
	private static final String SSL_CONTEXT_TYPE = "SSL";
	private static final String KEY_STORE_TYPE = "PKCS12";
	private static final String IN_WAS_TRUST_STORE = "was";
	private static final String IN_WAR_FILE = "inWar";
	private static final String IN_FILE_SYSTEM = "fileSystem";
	
	private String certificatePath;
	private String certificateKey;
	private String certificateLocationType;
	private boolean useCertificate = false;
	private String sslAlgorithm;
	private int httpsPort;
	private HttpClientParameters httpClientParameters;
	
	// An option is to pass a NoWaitThreadSafeClientConnManager
	// so that threads do not wait when the connections pool is entirely used
	private ClientConnectionManager clientConnectionManager;
	
	//
	// Created and configured in postConstruct, finalized in preDestroy
	//
	// We would annotate postConstruct with @PostConstruct
	// and preDestroy with @PreDestroy if run in a JSR-250 JRE.
	// But WAS is not JSR-250 compliant, so we have to call this method with
	// Spring's destroy-method
	//	
	private HttpClient httpClient;
	
	public void postConstruct() {
		if (null == clientConnectionManager) {
			log.warn("clientConnectionManager is null");
			httpClient = new DefaultHttpClient();
		}
		else {
			log.debug("Http client uses clientConnectionManager " + clientConnectionManager.getClass().toString());
			httpClient = new DefaultHttpClient(clientConnectionManager);
		}
		if (useCertificate) {
			log.debug("Applying certificate");
			httpClient = wrapClient(httpClient);
		}
	}	
	
	public void preDestroy() {		
		httpClient.getConnectionManager().shutdown();			
	}
	
	@Override
	protected HttpClient getHttpClient() {
		return httpClient;
	}
	
	private HttpClient wrapClient(HttpClient base) {
		log.debug("Wrapping client");	
		SSLContext sslContext = setupClientSSL(certificatePath, certificateKey, sslAlgorithm, certificateLocationType);
		log.trace("Got SSL context");		
		SSLSocketFactory ssf = new SSLSocketFactory(sslContext);
		ClientConnectionManager ccm = base.getConnectionManager();
		if (ccm instanceof ThreadSafeClientConnManager) {
			NoWaitThreadSafeClientConnManager.setMaxConnections((ThreadSafeClientConnManager) ccm, httpClientParameters);		
		}
		else {
			log.warn("Connection manager is not a ThreadSafeClientConnManager");
		}
		log.trace("Got client connection manager");		
		SchemeRegistry sr = ccm.getSchemeRegistry();
		log.trace("Got registry");
		sr.register(new Scheme("https", httpsPort, ssf));
		log.trace("Registered https scheme with port " + httpsPort);
		log.debug("Client wrapped");		
		return new DefaultHttpClient(ccm, base.getParams());
	}	
	
	private static SSLContext setupClientSSL(String certificatePath, String certificateKey, String sslAlgorithm, String certificateLocationType) {
		log.debug("certificatePath: " + certificatePath + "; certificateKey: " + certificateKey + "; sslAlgorithm: " + sslAlgorithm);
		try {			
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(sslAlgorithm);
			KeyStore ks = p12ToKeyStore(certificatePath, certificateKey, certificateLocationType);
			kmf.init(ks, certificateKey.toCharArray());
			SSLContext result = getSSLContext(kmf.getKeyManagers());
			log.trace("Got SSL context");
			return result;			
		}
		catch (NoSuchAlgorithmException ex) {
			final String errorMsg = "NoSuchAlgorithmException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);
		}
		catch (KeyManagementException ex) {
			final String errorMsg = "KeyManagementException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);
		}		
		catch (KeyStoreException ex) {
			final String errorMsg = "KeyStoreException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);
		}
		catch (UnrecoverableKeyException ex) {
			final String errorMsg = "UnrecoverableKeyException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);
		}
		catch (CertificateException ex) {
			final String errorMsg = "CertificateException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);
		}
		catch (NoSuchProviderException ex) {
			final String errorMsg = "NoSuchProviderException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);
		}
		catch (IOException ex) {
			final String errorMsg = "IOException: " + ex.getMessage();
			log.error(errorMsg);
			throw new HttpClientException(errorMsg);
		}
	}
	
	private static KeyStore p12ToKeyStore(String p12Path, String password,
			String certificateLocationType) throws NoSuchProviderException,
			KeyStoreException, CertificateException, NoSuchAlgorithmException,
			FileNotFoundException, IOException {	
		KeyStore result = KeyStore.getInstance(KEY_STORE_TYPE);
		InputStream is = null;
		try {
			if (IN_FILE_SYSTEM.equals(certificateLocationType)) {
				is = new FileInputStream(p12Path);
			}
			else if (IN_WAR_FILE.equals(certificateLocationType)) {
				is = CertificateEnabledApacheHttpClient4Wrapper.class
						.getResourceAsStream(p12Path);
			}
			else if (IN_WAS_TRUST_STORE.equals(certificateLocationType)) {
				log.warn("Get certificate from WAS truststore not implemented");
			}
			else {
				throw new HttpClientException("Not a valid certificate location type");
			}
			result.load(is, password.toCharArray());
		}
		finally {
			IOUtils.closeQuietly(is);
		}
		return result;
	}
	
	private static SSLContext getSSLContext(KeyManager[] keyManagers)
			throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext result = SSLContext.getInstance(SSL_CONTEXT_TYPE);
		SecureRandom random = SecureRandom.getInstance(SECURE_RANDOM_TYPE);
		random.setSeed(System.currentTimeMillis());
		result.init(keyManagers, null, random);
		return result;
	}
		
	public void setClientConnectionManager(ClientConnectionManager clientConnectionManager) {
		this.clientConnectionManager = clientConnectionManager;
	}
	
	public void setHttpClientParameters(HttpClientParameters httpClientParameters) {
		this.httpClientParameters = httpClientParameters;
	}	

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public void setCertificateKey(String certificateKey) {
		this.certificateKey = certificateKey;
	}

	public void setUseCertificate(boolean useCertificate) {
		this.useCertificate = useCertificate;
	}

	public void setSslAlgorithm(String sslAlgorithm) {
		this.sslAlgorithm = sslAlgorithm;
	}
	
	public void setCertificateLocationType(String certificateLocationType) {
		this.certificateLocationType = certificateLocationType;
	}
	
	public void setHttpsPort(int httpsPort) {
		this.httpsPort = httpsPort;
	}	
}