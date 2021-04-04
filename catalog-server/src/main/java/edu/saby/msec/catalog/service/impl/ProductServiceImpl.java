package edu.saby.msec.catalog.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.saby.msec.catalog.dao.ProductDao;
import edu.saby.msec.catalog.model.Product;
import edu.saby.msec.catalog.service.ProductService;
import edu.saby.msec.infra.service.impl.CRUDServiceImpl;

/**
 * @author Soumya Banerjee
 *
 */
@Service
public class ProductServiceImpl extends CRUDServiceImpl<Product> implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@PostConstruct
	void initialize() {
		initialize(productDao);
	}
	
}
