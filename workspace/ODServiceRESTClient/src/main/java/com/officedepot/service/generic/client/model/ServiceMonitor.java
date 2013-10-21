package com.officedepot.service.generic.client.model;

public class ServiceMonitor {
	private short failureCount;
	public boolean isServiceBroke() {
		return serviceBroke;
	}

	public void setServiceBroke(boolean serviceBroke) {
		this.serviceBroke = serviceBroke;
	}

	private boolean serviceBroke;

	public short getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(short failureCount) {
		this.failureCount = failureCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + failureCount;
		result = prime * result + (serviceBroke ? 1231 : 1237);
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
		ServiceMonitor other = (ServiceMonitor) obj;
		if (failureCount != other.failureCount)
			return false;
		if (serviceBroke != other.serviceBroke)
			return false;
		return true;
	}

	 

}
