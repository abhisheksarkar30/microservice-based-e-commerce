package edu.saby.msec.infra.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import edu.saby.msec.infra.exceptions.DataAccessException;

/**
 * 
 * @author Abhishek Sarkar & Soumya Banerjee
 *
 * @param <T>
 */
public interface GenericDao<T> {
	
	/**
     * This method delete given object from the database.
     *
     * @param id - Object id to load
     * @throws DataAccessException - throws if an error occurs
     */
    T getById(Object id) throws DataAccessException;

    /**
     * This method queries all the objects
     *
     * @throws DataAccessException - throws if an error occurs
     */
    List<T> getAll() throws DataAccessException;

    /**
     * This method insert a given object to the database.
     *
     * @param object - instance of Object class
     * @throws DataAccessException - throws if an error occurs
     */
    T add(T object) throws DataAccessException;

    /**
     * This method update given object in the database.
     *
     * @param object - instance of Object class
     * @throws DataAccessException - throws if an error occurs
     */
    T update(T object) throws DataAccessException;
    
    /**
     * This method delete given object from the database.
     *
     * @param object - instance of Object class
     * @throws DataAccessException - throws if an error occurs
     */
    T delete(T object) throws DataAccessException;
    
    /**
     * This method fetches objects specified by given fields from the database.
     *
     * @param id - Object id to load
     * @throws DataAccessException - throws if an error occurs
     */
    List<T> getByFields(Query query) throws DataAccessException;
}
