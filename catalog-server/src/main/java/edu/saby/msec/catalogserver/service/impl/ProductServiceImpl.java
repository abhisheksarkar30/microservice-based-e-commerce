package edu.saby.msec.catalogserver.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.saby.msec.catalogserver.dao.ProductDao;
import edu.saby.msec.catalogserver.model.Product;
import edu.saby.msec.catalogserver.service.ProductService;
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
