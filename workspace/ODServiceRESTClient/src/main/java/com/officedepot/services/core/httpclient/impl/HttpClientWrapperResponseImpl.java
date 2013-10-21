package com.officedepot.services.core.httpclient.impl;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.http.cookie.Cookie;

import com.officedepot.services.core.httpclient.HttpClientWrapperResponse;

public class HttpClientWrapperResponseImpl implements HttpClientWrapperResponse {

	private String data;	
	private Set<Cookie> cookies;
	
	public HttpClientWrapperResponseImpl(String data, Set<Cookie> cookies) {
		this.data = data;
		this.cookies = cookies;
	}
	
	public Set<Cookie> getCookies() {
		return cookies;
	}
	
	public void setCookies(Set<Cookie> cookies) {
		this.cookies = cookies;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).
				append(cookies).
				append(data).			
				toString();
	}	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().
				append(cookies).
				append(data).				
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
		if (! (obj instanceof HttpClientWrapperResponse)) {
			return false;
		}
		HttpClientWrapperResponse other = (HttpClientWrapperResponse) obj;
		return new EqualsBuilder().
				append(cookies, other.getCookies()).
				append(data, other.getData()).
				isEquals();
	}	
}
