package edu.saby.msec.infra.async.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import rx.Observable;
import rx.Single;

/**
 * @author Soumya Banerjee
 *
 * @param <T>
 */
public class AsyncResponseFluentBuilder<T> {

	private AsyncResponseFluentBuilder() {

	}

	public static <T> StatusStep<T> newBuilder() {
		return new AsyncResponseSteps<T>();
	}

	public interface StatusStep<T> {
		ResponseTypeStep<T> status(HttpStatus httpStatus);
	}

	public interface ResponseTypeStep<T> {
		FinalStep<T> single(Single<T> singleValue);

		FinalStep<T> observable(Observable<T> observable);
	}

	public interface FinalStep<T> {
		FinalStep<T> headers(MultiValueMap<String, String> headers);

		AsyncResponse<T> build();
	}

	private static class AsyncResponseSteps<T> implements StatusStep<T>, ResponseTypeStep<T>, FinalStep<T> {

		private Single<T> singleValue;

		private Observable<T> observable;

		private HttpStatus httpStatus;

		private MultiValueMap<String, String> headers;

		@Override
		public ResponseTypeStep<T> status(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
			return this;
		}

		@Override
		public FinalStep<T> single(Single<T> singleValue) {
			this.singleValue = singleValue;
			return this;
		}

		@Override
		public FinalStep<T> observable(Observable<T> observable) {
			this.observable = observable;
			return this;
		}

		@Override
		public FinalStep<T> headers(MultiValueMap<String, String> headers) {
			this.headers = headers;
			return this;
		}

		@Override
		public AsyncResponse<T> build() {
			AsyncResponse<T> response = null;
			if (this.singleValue != null) {
				response = new AsyncResponse<>(singleValue, headers, httpStatus);
			} else {
				response = new AsyncResponse<>(observable, headers, httpStatus);
			}
			return response;
		}

	}
}
