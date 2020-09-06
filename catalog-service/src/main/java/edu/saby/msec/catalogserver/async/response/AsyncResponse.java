package edu.saby.msec.catalogserver.async.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import rx.Observable;
import rx.Single;

public class AsyncResponse<T> {
	private Observable<T> observable;
	private Single<T> singleValue;
	private MultiValueMap<String, String> headers;
	private HttpStatus httpStatus;
	
	public AsyncResponse(Observable<T> observable, MultiValueMap<String, String> headers, HttpStatus status) {
		this.observable = observable;
		this.headers = headers;
		this.httpStatus = status;
	}
	
	public AsyncResponse(Single<T> singleValue, MultiValueMap<String, String> headers, HttpStatus status) {
		this.singleValue = singleValue;
		this.headers = headers;
		this.httpStatus = status;
	}
	
	/**
	 * @return the observable
	 */
	public Observable<T> getObservable() {
		return observable;
	}
	/**
	 * @param observable the observable to set
	 */
	public void setObservable(Observable<T> observable) {
		this.observable = observable;
	}
	/**
	 * @return the singleValue
	 */
	public Single<T> getSingleValue() {
		return singleValue;
	}
	/**
	 * @param singleValue the singleValue to set
	 */
	public void setSingleValue(Single<T> singleValue) {
		this.singleValue = singleValue;
	}
	/**
	 * @return the headers
	 */
	public MultiValueMap<String, String> getHeaders() {
		return headers;
	}
	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(MultiValueMap<String, String> headers) {
		this.headers = headers;
	}
	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
