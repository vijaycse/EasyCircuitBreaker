package com.officedepot.services.core.http;

import java.util.List;
import java.util.Set;

import com.officedepot.services.core.model.ServiceMethodActualParameter;
import com.officedepot.services.core.model.ServiceMethodActualResult;
import com.officedepot.services.core.model.ServiceMethodCookie;
import com.officedepot.services.core.model.ServiceMethodCall.HttpMethodType;

public interface ServiceHttpClient {

	ServiceMethodActualResult executeJson(
			String uri,
			HttpMethodType methodType,
			String json,
			Set<ServiceMethodCookie> cookies
	);
	
	ServiceMethodActualResult executeGetJson(
			String uri
	);
	
	ServiceMethodActualResult executeNvp(
			String uri,
			HttpMethodType methodType,
			List<ServiceMethodActualParameter> parameters,
			Set<ServiceMethodCookie> cookies
	);

	ServiceMethodActualResult execute(
			String uri,
			HttpMethodType methodType,
			List<ServiceMethodActualParameter> headers
	);
}