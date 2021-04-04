package edu.saby.msec.catalog.dao.impl;

import org.springframework.stereotype.Repository;

import edu.saby.msec.catalog.dao.ProductDao;
import edu.saby.msec.catalog.model.Product;
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
