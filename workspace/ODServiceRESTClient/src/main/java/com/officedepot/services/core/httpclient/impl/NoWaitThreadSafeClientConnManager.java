package com.officedepot.services.core.httpclient.impl;

import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.log4j.Logger;

import com.officedepot.services.core.httpclient.HttpClientParameters;
import com.officedepot.services.core.httpclient.exceptions.HttpClientNoConnectionAvailableException;

// Simplistic version with no discrimination on the routes while waiting for instructions
// regarding HttpClient 4.1.1 versus 4.2.x.
public class NoWaitThreadSafeClientConnManager extends ThreadSafeClientConnManager {
	
	private final static Logger log = Logger.getLogger(NoWaitThreadSafeClientConnManager.class);
	
	public NoWaitThreadSafeClientConnManager() {
		// empty
	}
	
	public NoWaitThreadSafeClientConnManager(SchemeRegistry schemeRegistry) {
		super(schemeRegistry);
	}	
	
	public ClientConnectionRequest requestConnection(final HttpRoute route, final Object state) {
		// for the time being, we do not discriminate between routes
		final int max = getMaxTotal();
		final int used = getConnectionsInPool();
		log.debug("Using " + used + " connections out of " + max);
		if (used == max) {
			log.error("No connection available");
			throw new HttpClientNoConnectionAvailableException("No connection available");
		}
		return super.requestConnection(route, state);
	}

	public static void setMaxConnections(ThreadSafeClientConnManager manager, HttpClientParameters parameters) {
		manager.setMaxTotal(parameters.getMaxConnections());
		manager.setDefaultMaxPerRoute(parameters.getMaxConnections());		
	}
}