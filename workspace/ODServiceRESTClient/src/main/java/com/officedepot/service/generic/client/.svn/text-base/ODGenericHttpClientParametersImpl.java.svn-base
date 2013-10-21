package com.officedepot.service.generic.client;

import com.officedepot.services.core.httpclient.impl.HttpClientParametersImpl;

public class ODGenericHttpClientParametersImpl extends HttpClientParametersImpl {

	private int maxConnections;

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxConnections;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ODGenericHttpClientParametersImpl other = (ODGenericHttpClientParametersImpl) obj;
		if (maxConnections != other.maxConnections)
			return false;
		return true;
	}

}
