package edu.saby.msec.infra.controller;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.saby.msec.infra.async.response.AsyncResponse;
import edu.saby.msec.infra.exceptions.DataAccessException;
import edu.saby.msec.infra.service.CRUDService;

/**
 * @author Soumya Banerjee & Abhishek Sarkar
 *
 */
public class GenericEntityController<T> extends AbstractController {

	@Autowired
	private CRUDService<T> entityService;

	@RequestMapping
	public AsyncResponse<T> getAll() {
		return makeAsyncResponse(entityService.getAll());
	}

	@RequestMapping("/{id}")
	public AsyncResponse<T> getEntityById(@PathVariable("id") String id) {
		return makeAsyncResponse(entityService.getById(id));
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public AsyncResponse<T> addEntity(@ModelAttribute T entity) {
		return makeAsyncResponse(entityService.add(entity), HttpStatus.CREATED);
	}

	@RequestMapping(path = "/edit", method = RequestMethod.PUT)
	public AsyncResponse<T> editEntity(@ModelAttribute T entity) {
		return makeAsyncResponse(entityService.edit(entity), HttpStatus.ACCEPTED);
	}

	@RequestMapping("/search/key={field}&val={value}")
	public AsyncResponse<T> getEntitiesByField(@PathVariable("field") String field, @PathVariable("value") String value) {
		Query query = new Query(Criteria.where(field).is(value));
		return makeAsyncResponse(entityService.getByFields(query));
	}
	
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public AsyncResponse<T> getEntitiesByFields(@ModelAttribute T entity) throws DataAccessException {
		Query query = new Query();
		
		Class<? extends Object> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields) {
			String fieldName = field.getName();
			String fieldType = field.getType().getSimpleName();
			String getterMethodName = (fieldType.equals("boolean")? "is" : "get")
				     + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Object value = clazz.getMethod(getterMethodName).invoke(entity);
				
				if(value != null)
					query.addCriteria(Criteria.where(fieldName).is(value));
			} catch (Exception e) {
				throw new DataAccessException(e);
			}
		}
		
		return makeAsyncResponse(entityService.getByFields(query));
	}
}
