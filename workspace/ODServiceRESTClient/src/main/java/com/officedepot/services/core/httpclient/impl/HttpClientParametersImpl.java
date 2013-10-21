package com.officedepot.services.core.httpclient.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.officedepot.services.core.httpclient.HttpClientParameters;

public class HttpClientParametersImpl implements HttpClientParameters {

	private int maxConnections;
	private int connectionTimeoutMillis;
	private int socketTimeoutMillis;
	
	public int getMaxConnections() {
		return maxConnections;
	}
	
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	
	public int getConnectionTimeoutMillis() {
		return connectionTimeoutMillis;
	}
	
	public void setConnectionTimeoutMillis(int connectionTimeoutMillis) {
		this.connectionTimeoutMillis = connectionTimeoutMillis;
	}
	
	public int getSocketTimeoutMillis() {
		return socketTimeoutMillis;
	}
	
	public void setSocketTimeoutMillis(int socketTimeoutMillis) {
		this.socketTimeoutMillis = socketTimeoutMillis;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).
			append(maxConnections).
			append(connectionTimeoutMillis).
			append(socketTimeoutMillis).
			toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().
			append(maxConnections).
			append(connectionTimeoutMillis).
			append(socketTimeoutMillis).
			toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (! (obj instanceof HttpClientParameters)) {
			return false;
		}
		HttpClientParameters other = (HttpClientParameters) obj;
		return new EqualsBuilder()
			.append(maxConnections, other.getMaxConnections())
			.append(connectionTimeoutMillis, other.getConnectionTimeoutMillis())
			.append(socketTimeoutMillis, other.getSocketTimeoutMillis())			
			.isEquals();
	}	
}
