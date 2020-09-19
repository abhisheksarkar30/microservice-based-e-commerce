package edu.saby.msec.catalogserver.service;

import org.springframework.data.mongodb.core.query.Query;

import rx.Observable;
import rx.Single;

/**
 * @author Soumya Banerjee & Abhishek Sarkar
 *
 * @param <T>
 */
public interface CRUDService<T> {
	
	Single<T> getById(String id);

	Single<T> add(T obj);

	Single<T> edit(T obj);

	Single<Boolean> delete(T object);

	Observable<T> getAll();
	
	Observable<T> getByFields(Query query);
	
}
