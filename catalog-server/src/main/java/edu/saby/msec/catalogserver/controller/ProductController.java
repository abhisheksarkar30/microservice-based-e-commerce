package edu.saby.msec.catalogserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.saby.msec.catalogserver.async.response.AsyncResponse;
import edu.saby.msec.catalogserver.model.Product;
import edu.saby.msec.catalogserver.service.ProductService;

/**
 * @author Soumya Banerjee
 *
 */
@RestController
@RequestMapping("/catalog-server")
public class ProductController extends AbstractController{
	@Autowired
	private ProductService productService;
	
	@RequestMapping
    public AsyncResponse<Product> getAll() {
		return makeAsyncResponse(productService.getAll());
    }
	
	@RequestMapping("/{id}")
    public AsyncResponse<Product> getProductById(@PathVariable("id") String id) {
		return makeAsyncResponse(productService.getById(id));
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public AsyncResponse<Product> add(@ModelAttribute Product product) {
    	return makeAsyncResponse(productService.add(product), HttpStatus.CREATED);
    }
	
	@RequestMapping("/addProduct/name={productName}&price={price}")
	public AsyncResponse<Product> addProduct(@PathVariable("productName") String productName, @PathVariable("price") Double price) {
    	Product product = new Product();
    	product.setName(productName);
    	product.setPrice(price);
		return makeAsyncResponse(productService.add(product), HttpStatus.CREATED);
    }
	
    @RequestMapping(method = RequestMethod.PUT)
    public AsyncResponse<Product> edit(@ModelAttribute Product product) {
    	return makeAsyncResponse(productService.edit(product), HttpStatus.ACCEPTED);
    }
}
