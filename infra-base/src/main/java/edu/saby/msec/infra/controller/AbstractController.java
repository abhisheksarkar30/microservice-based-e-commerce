package edu.saby.msec.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import edu.saby.msec.infra.async.response.AsyncResponse;
import edu.saby.msec.infra.async.response.AsyncResponseFluentBuilder;
import rx.Observable;
import rx.Single;

/**
 * @author Soumya Banerjee
 *
 */
public abstract class AbstractController {
	
	protected <T> ResponseEntity<T> makeResponse(T message) {
		return makeResponse(message, null, HttpStatus.OK);
	}

	protected <T> ResponseEntity<T> makeResponse(T message, HttpStatus status) {
		return makeResponse(message, null, status);
	}
	
	protected <T> ResponseEntity<T> makeResponse(T message, MultiValueMap<String, String> headers, HttpStatus status) {
		return new ResponseEntity<T>(message, headers, status);
	}
	
	/*Observable Asynchronous Responses*/
	protected <T> AsyncResponse<T> makeAsyncResponse(Observable<T> observable) {
		return makeAsyncResponse(observable, null, HttpStatus.OK);
	}

	protected <T> AsyncResponse<T> makeAsyncResponse(Observable<T> observable, HttpStatus status) {
		return makeAsyncResponse(observable, null, status);
	}
	
	protected <T> AsyncResponse<T> makeAsyncResponse(Observable<T> observable, MultiValueMap<String, String> headers,HttpStatus httpStatus) {
		return AsyncResponseFluentBuilder.<T>newBuilder().status(httpStatus).observable(observable).headers(headers).build();
	}
	
	/*Single Asynchronous Responses*/
	protected <T> AsyncResponse<T> makeAsyncResponse(Single<T> single) {
		return makeAsyncResponse(single, null, HttpStatus.OK);
	}
	
	protected <T> AsyncResponse<T> makeAsyncResponse(Single<T> single, HttpStatus status) {
		return makeAsyncResponse(single, null, status);
	}

	protected <T> AsyncResponse<T> makeAsyncResponse(Single<T> single, MultiValueMap<String, String> headers,HttpStatus httpStatus) {
		return AsyncResponseFluentBuilder.<T>newBuilder().status(httpStatus).single(single).headers(headers).build();
	}
	
}
