package edu.saby.msec.infra.async.handlers;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import edu.saby.msec.infra.async.deferred.ObservableDeferredResult;
import edu.saby.msec.infra.async.deferred.SingleDeferredResult;
import edu.saby.msec.infra.async.response.AsyncResponse;
import rx.Observable;
import rx.Single;

/**
 * @author Soumya Banerjee
 *
 */
public class AsynchronousResponseHandler implements AsyncHandlerMethodReturnValueHandler{

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer container, NativeWebRequest webRequest)
			throws Exception {
		if (returnValue == null) {
            container.setRequestHandled(true);
            return;
        }
		final AsyncResponse<?> asyncResponse = AsyncResponse.class.cast(returnValue);
        
        Observable<?> observable = asyncResponse.getObservable();
        Single<?> single = asyncResponse.getSingleValue();
        MultiValueMap<String, String> headers =  asyncResponse.getHeaders();
        HttpStatus status = asyncResponse.getHttpStatus();
        
        if(observable != null)
        	WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(new ObservableDeferredResult<>(observable, headers, status), container);
        else if(single != null)
        	WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(new SingleDeferredResult<>(single, headers, status), container);
        
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return AsyncResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
		return returnValue != null && supportsReturnType(returnType);
	}

}
