package com.officedepot.services.core.httpclient;

import java.util.Set;

import org.apache.http.cookie.Cookie;

public interface HttpClientWrapperResponse {

	Set<Cookie> getCookies();
	
	String getData();
}
