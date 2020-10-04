package edu.saby.msec.catalogserver.dao.impl;

import org.springframework.stereotype.Repository;

import edu.saby.msec.catalogserver.dao.ProductDao;
import edu.saby.msec.catalogserver.model.Product;
import edu.saby.msec.infra.dao.impl.GenericDaoImpl;

/**
 * @author Soumya Banerjee
 *
 */
@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	public ProductDaoImpl() {
		super(Product.class);
	}
}
