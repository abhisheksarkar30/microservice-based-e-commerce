package edu.saby.msec.infra.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import edu.saby.msec.infra.dao.GenericDao;
import edu.saby.msec.infra.exceptions.DataAccessException;

/**
 * 
 * @author Abhishek Sarkar & Soumya Banerjee
 *
 * @param <T>
 */
public class GenericDaoImpl<T> implements GenericDao<T> {

	private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

	@Autowired
	protected MongoOperations mongoOperations;

	private Class<T> type;

	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	@Override
	public T add(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} add", type);
		try {
			mongoOperations.insert(object);
			return object;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public T update(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} modify", type);
		try {
			mongoOperations.save(object);
			return object;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public T delete(T object) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} delete", type);
		try {
			mongoOperations.remove(object);
			return object;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}

	}

	@Override
	public T getById(Object id) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getById", type);
		try {
			return mongoOperations.findById(id, type);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<T> getAll() throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getAll", type);
		try {
			return mongoOperations.findAll(type);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	@Override
	public List<T> getByFields(Query query) throws DataAccessException {
		if (logger.isDebugEnabled())
			logger.debug("type {} getByFields", type);
		try {
			return mongoOperations.find(query, type);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
