package com.officedepot.services.core.api;

import com.officedepot.services.core.model.ServiceMethodActualResult;
import com.officedepot.services.core.model.ServiceMethodCall;

public interface VendorServiceClient {
	
	ServiceMethodActualResult execute(ServiceMethodCall call);	
}