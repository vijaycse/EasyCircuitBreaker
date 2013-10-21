package com.officedepot.service.generic.client.resource.impl;

import static com.officedepot.service.generic.client.util.GenericClientUtility.dateFormatter;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.neverquitter.service.generic.client.monitor.ServiceHealth;
import com.neverquitter.service.generic.client.task.GenericClientHTTPTask;
import com.neverquitter.service.generic.client.task.CustomRestCallExecutor;
import com.neverquitter.service.generic.client.task.CustomRestClientThreadPoolExecutor;
import com.officedepot.service.generic.client.impl.ODServiceRESTClientResponse;
import com.officedepot.service.generic.client.model.GenericClientException;
import com.officedepot.service.generic.client.resource.GenericClientHTTPResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public class GenericClientHTTPResponseImpl implements GenericClientHTTPResponse{

	private ServiceHealth HEALTH_CHECK;
	private final static Logger LOGGER = Logger.getLogger(GenericClientHTTPResponseImpl.class);
	
	public GenericClientHTTPResponseImpl(ServiceHealth healthCheck){
		this.HEALTH_CHECK= healthCheck;
	}


	public ServiceHealth getHEALTH_CHECK() {
		return HEALTH_CHECK;
	}
	public void setHEALTH_CHECK(ServiceHealth hEALTH_CHECK) {
		HEALTH_CHECK = hEALTH_CHECK;
	}
	

	/***
	 *  Creates callable task and returns generic http response
	 * @param requestResource
	 * @param serviceName
	 * @param httpMethod
	 * @param minThreads
	 * @param maxThreads
	 * @param keepAliveTimeout
	 * @param queueSize
	 * @return ODServiceRESTClientResponse
	 * @throws GenericClientException 
	 */
	@SuppressWarnings("unchecked")
	public final <T> T obtainGenericTypeResponse(final Builder requestResource,
			final Class<T> expectedPojo, final String serviceName,final String httpMethod,final short minThreds, final short maxThreads,final long keepAliveTimeout,final short queueSize)
					throws GenericClientException {
		final String methodName = " createServiceCallTask() - GenericTemplate  for  Service " + serviceName + " ";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting of " + methodName + dateFormatter().format(new Date(System.currentTimeMillis())));
		}
		T anyType = null;
		// submitting the task
		try {
			CustomRestClientThreadPoolExecutor threadPool = CustomRestCallExecutor.REST_CALL_EXECUTOR.fetchODThreadPoolInstance(
					minThreds, 
					maxThreads, 
					keepAliveTimeout,
					serviceName,
					queueSize);
			Future<?> result = threadPool.submitTasks(new GenericClientHTTPTask<T>(requestResource, methodName, expectedPojo,false));
			// Waiting for the result to be available and retrieve it from the Future
			anyType = (T) result.get();
		} catch (InterruptedException e) {
			LOGGER.error(e);
			throw new GenericClientException(" Interrupted Exception ",	e.getMessage());
		} catch (ExecutionException e) {
			LOGGER.error(e);
			 getHEALTH_CHECK().updateFailure(serviceName);
			throw new GenericClientException(" ExecutionException ",e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e);
			 getHEALTH_CHECK().updateFailure(serviceName);
			throw new GenericClientException(" Exception -Thread ",	e.getMessage());
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Ending of "+ methodName+ dateFormatter().format(new Date(System.currentTimeMillis())));
		}
		return anyType;
	}

	
	

	/***
	 *  Creates callable task and returns http response
	 * @param requestResource
	 * @param serviceName
	 * @param httpMethod
	 * @param minThreads
	 * @param maxThreads
	 * @param keepAliveTimeout
	 * @param queueSize
	 * @return ODServiceRESTClientResponse
	 * @throws GenericClientException 
	 */
	public final <T> ODServiceRESTClientResponse obtainHttpResponse(final Builder requestResource,final String serviceName,final String httpMethod, final short minThreads, final short maxThreads,
			final long keepAliveTimeout, final	short queueSize) throws GenericClientException {
		final String methodName = " createServiceCallTask() - GenericTemplate  for  Service " + serviceName + " ";
		ODServiceRESTClientResponse httpResponse = null;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Starting of " + methodName + dateFormatter().format(new Date(System.currentTimeMillis())));
		}
		// submitting the task
		try {
			CustomRestClientThreadPoolExecutor threadPool = CustomRestCallExecutor.REST_CALL_EXECUTOR.fetchODThreadPoolInstance(
					minThreads, 
					maxThreads, 
					keepAliveTimeout,
					serviceName,
					queueSize);
			Future<?> result = threadPool.submitTasks(new GenericClientHTTPTask<T>(requestResource, methodName, null,true));
			httpResponse = (ODServiceRESTClientResponse) result.get();
		}catch (InterruptedException e) {
			LOGGER.error(e);
			throw new GenericClientException(" Interrupted Exception ",	e.getMessage());
		} catch (ExecutionException e) {
			LOGGER.error(e);
			 getHEALTH_CHECK().updateFailure(serviceName);
			throw new GenericClientException(" ExecutionException ",e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e);
			 getHEALTH_CHECK().updateFailure(serviceName);
			throw new GenericClientException(" Exception -Thread ",	e.getMessage());
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Ending of "+ methodName+ dateFormatter().format(new Date(System.currentTimeMillis())));
		}
		return httpResponse;
	}


	 



	
}