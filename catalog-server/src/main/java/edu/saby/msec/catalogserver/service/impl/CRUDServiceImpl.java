package edu.saby.msec.catalogserver.service.impl;

import edu.saby.msec.catalogserver.dao.GenericDao;
import edu.saby.msec.catalogserver.exceptions.DataAccessException;
import edu.saby.msec.catalogserver.service.CRUDService;
import rx.Observable;
import rx.Single;
import rx.exceptions.Exceptions;

/**
 * @author Soumya Banerjee
 *
 * @param <T>
 */
public abstract class CRUDServiceImpl<T> implements CRUDService<T> {

	protected GenericDao<T> genericDao;

	protected void initialize(GenericDao<T> dao) {
		this.genericDao = dao;
	}

	@Override
	public Single<T> getById(String id) {
		try {
			return Single.just(genericDao.getById(id)).map(o -> {
				if (o == null)
					// TODO change the exception type
					throw Exceptions.propagate(new DataAccessException());
				return o;
			});
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<T> add(T obj) {
		try {
			genericDao.add(obj);
			return Single.just(obj);
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<T> edit(T obj) {
		try {
			genericDao.update(obj);
			return Single.just(obj);
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<Boolean> delete(T object) {
		try {
			genericDao.delete(object);
			return Single.just(true);
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Observable<T> getAll() {
		try {
			return Observable.from(genericDao.getAll());
		} catch (DataAccessException de) {
			return Observable.error(de);
		}
	}

}
