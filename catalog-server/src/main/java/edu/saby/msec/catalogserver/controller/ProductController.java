package edu.saby.msec.catalogserver.controller;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.saby.msec.catalogserver.async.response.AsyncResponse;
import edu.saby.msec.catalogserver.exceptions.DataAccessException;
import edu.saby.msec.catalogserver.model.Product;
import edu.saby.msec.catalogserver.service.ProductService;

/**
 * @author Soumya Banerjee & Abhishek Sarkar
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController extends AbstractController {

	@Autowired
	private ProductService productService;

	@RequestMapping
	public AsyncResponse<Product> getAll() {
		return makeAsyncResponse(productService.getAll());
	}

	@RequestMapping("/{id}")
	public AsyncResponse<Product> getEntityById(@PathVariable("id") String id) {
		return makeAsyncResponse(productService.getById(id));
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public AsyncResponse<Product> addEntity(@ModelAttribute Product product) {
		return makeAsyncResponse(productService.add(product), HttpStatus.CREATED);
	}

	@RequestMapping("/add/name={productName}&price={price}")
	public AsyncResponse<Product> addEntity(@PathVariable("productName") String productName, @PathVariable("price") Double price) {
		Product product = new Product();
		product.setName(productName);
		product.setPrice(price);
		return makeAsyncResponse(productService.add(product), HttpStatus.CREATED);
	}

	@RequestMapping(path = "/edit", method = RequestMethod.PUT)
	public AsyncResponse<Product> editEntity(@ModelAttribute Product product) {
		return makeAsyncResponse(productService.edit(product), HttpStatus.ACCEPTED);
	}

	@RequestMapping("/search/key={field}&val={value}")
	public AsyncResponse<Product> getEntitiesByField(@PathVariable("field") String field, @PathVariable("value") String value) {
		Query query = new Query(Criteria.where(field).is(value));
		return makeAsyncResponse(productService.getByFields(query));
	}
	
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public AsyncResponse<Product> getEntitiesByFields(@ModelAttribute Product product) throws DataAccessException {
		Query query = new Query();
		
		Class<Product> clazz = Product.class;
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields) {
			String fieldName = field.getName();
			String fieldType = field.getType().getSimpleName();
			String getterMethodName = (fieldType.equals("boolean")? "is" : "get")
				     + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				Object value = clazz.getMethod(getterMethodName).invoke(product);
				
				if(value != null)
					query.addCriteria(Criteria.where(fieldName).is(value));
			} catch (Exception e) {
				throw new DataAccessException(e);
			}
		}
		
		return makeAsyncResponse(productService.getByFields(query));
	}
}
