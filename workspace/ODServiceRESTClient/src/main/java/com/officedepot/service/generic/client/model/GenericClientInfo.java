package com.officedepot.service.generic.client.model;

public class GenericClientInfo {

	// thread pool
	private String minThreads;
	// thread pool
	private String maxThreads;
	// thread pool
	private String queueSize;
	// thread pool task timeout

	// get or post
	private String httpMethod;
	// connection pool settings
	private String maxConnection;
	// connection pool setting
	private String connectionTimeout;
	// circuit breaker failure count
	private String failureCountAllowed;
	// circuit breaker timer
	private String serviceBreakerTimer;
	// content type
	private String contentType;
	// service Name
	private String serviceName;
	// Username for HTTP basic authentication
	private String basicAuthenticationUsername;
	// Password for HTTP basic authentication
	private String basicAuthenticationPassword;

	public String getMinThreads() {
		return minThreads;
	}

	public void setMinThreads(String minThreads) {
		this.minThreads = minThreads;
	}

	public String getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(String maxThreads) {
		this.maxThreads = maxThreads;
	}

	public String getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(String queueSize) {
		this.queueSize = queueSize;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	private String keepAliveTimeout;

	public String getKeepAliveTimeout() {
		return keepAliveTimeout;
	}

	public void setKeepAliveTimeout(String keepAliveTimeout) {
		this.keepAliveTimeout = keepAliveTimeout;
	}

	public String getMaxConnection() {
		return maxConnection;
	}

	public void setMaxConnection(String maxConnection) {
		this.maxConnection = maxConnection;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getFailureCountAllowed() {
		return failureCountAllowed;
	}

	public void setFailureCountAllowed(String failureCountAllowed) {
		this.failureCountAllowed = failureCountAllowed;
	}

	public String getServiceBreakerTimer() {
		return serviceBreakerTimer;
	}

	public void setServiceBreakerTimer(String serviceBreakerTimer) {
		this.serviceBreakerTimer = serviceBreakerTimer;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBasicAuthenticationUsername() {
		return basicAuthenticationUsername;
	}

	public void setBasicAuthenticationUsername(
			String basicAuthenticationUsername) {
		this.basicAuthenticationUsername = basicAuthenticationUsername;
	}

	public String getBasicAuthenticationPassword() {
		return basicAuthenticationPassword;
	}

	public void setBasicAuthenticationPassword(
			String basicAuthenticationPassword) {
		this.basicAuthenticationPassword = basicAuthenticationPassword;
	}
	
	
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((basicAuthenticationPassword == null) ? 0
						: basicAuthenticationPassword.hashCode());
		result = prime
				* result
				+ ((basicAuthenticationUsername == null) ? 0
						: basicAuthenticationUsername.hashCode());
		result = prime
				* result
				+ ((connectionTimeout == null) ? 0 : connectionTimeout
						.hashCode());
		result = prime * result
				+ ((contentType == null) ? 0 : contentType.hashCode());
		result = prime
				* result
				+ ((failureCountAllowed == null) ? 0 : failureCountAllowed
						.hashCode());
		result = prime * result
				+ ((httpMethod == null) ? 0 : httpMethod.hashCode());
		result = prime
				* result
				+ ((keepAliveTimeout == null) ? 0 : keepAliveTimeout.hashCode());
		result = prime * result
				+ ((maxConnection == null) ? 0 : maxConnection.hashCode());
		result = prime * result
				+ ((maxThreads == null) ? 0 : maxThreads.hashCode());
		result = prime * result
				+ ((minThreads == null) ? 0 : minThreads.hashCode());
		result = prime * result
				+ ((queueSize == null) ? 0 : queueSize.hashCode());
		result = prime
				* result
				+ ((serviceBreakerTimer == null) ? 0 : serviceBreakerTimer
						.hashCode());
		result = prime * result
				+ ((serviceName == null) ? 0 : serviceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericClientInfo other = (GenericClientInfo) obj;
		if (basicAuthenticationPassword == null) {
			if (other.basicAuthenticationPassword != null)
				return false;
		} else if (!basicAuthenticationPassword
				.equals(other.basicAuthenticationPassword))
			return false;
		if (basicAuthenticationUsername == null) {
			if (other.basicAuthenticationUsername != null)
				return false;
		} else if (!basicAuthenticationUsername
				.equals(other.basicAuthenticationUsername))
			return false;
		if (connectionTimeout == null) {
			if (other.connectionTimeout != null)
				return false;
		} else if (!connectionTimeout.equals(other.connectionTimeout))
			return false;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (failureCountAllowed == null) {
			if (other.failureCountAllowed != null)
				return false;
		} else if (!failureCountAllowed.equals(other.failureCountAllowed))
			return false;
		if (httpMethod == null) {
			if (other.httpMethod != null)
				return false;
		} else if (!httpMethod.equals(other.httpMethod))
			return false;
		if (keepAliveTimeout == null) {
			if (other.keepAliveTimeout != null)
				return false;
		} else if (!keepAliveTimeout.equals(other.keepAliveTimeout))
			return false;
		if (maxConnection == null) {
			if (other.maxConnection != null)
				return false;
		} else if (!maxConnection.equals(other.maxConnection))
			return false;
		if (maxThreads == null) {
			if (other.maxThreads != null)
				return false;
		} else if (!maxThreads.equals(other.maxThreads))
			return false;
		if (minThreads == null) {
			if (other.minThreads != null)
				return false;
		} else if (!minThreads.equals(other.minThreads))
			return false;
		if (queueSize == null) {
			if (other.queueSize != null)
				return false;
		} else if (!queueSize.equals(other.queueSize))
			return false;
		if (serviceBreakerTimer == null) {
			if (other.serviceBreakerTimer != null)
				return false;
		} else if (!serviceBreakerTimer.equals(other.serviceBreakerTimer))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "GenericClientInfo [minThreads=" + minThreads + ", maxThreads="
				+ maxThreads + ", queueSize=" + queueSize + ", httpMethod="
				+ httpMethod + ", maxConnection=" + maxConnection
				+ ", connectionTimeout=" + connectionTimeout
				+ ", failureCountAllowed=" + failureCountAllowed
				+ ", serviceBreakerTimer=" + serviceBreakerTimer
				+ ", contentType=" + contentType + ", serviceName="
				+ serviceName + ", basicAuthenticationUsername="
				+ basicAuthenticationUsername
				+ ", basicAuthenticationPassword="
				+ basicAuthenticationPassword + ", keepAliveTimeout="
				+ keepAliveTimeout + "]";
	}

	private static String maskSensitiveData(String str) {
		return (null == str) ? "null" : "*****";
	}
}
