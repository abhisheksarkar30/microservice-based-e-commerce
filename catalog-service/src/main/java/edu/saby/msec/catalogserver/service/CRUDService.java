package edu.saby.msec.catalogserver.service;

import rx.Observable;
import rx.Single;

public interface CRUDService<T> {
	Single<T> getById(String id);

	Single<T> add(T obj);

	Single<T> edit(T obj);

	Single<Boolean> delete(T object);

	Observable<T> getAll();
}
