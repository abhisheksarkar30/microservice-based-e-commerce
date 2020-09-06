package edu.saby.msec.catalogserver.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.saby.msec.catalogserver.model.Product;
import edu.saby.msec.catalogserver.service.ProductService;
import rx.Observable;
import rx.Single;

@Service
public class ProductServiceImpl extends CRUDServiceImpl<Product> implements ProductService {

	@Override
	public Single<Product> getById(String id) {
		Product product = new Product();
		product.setSellerId("Someone");
		product.setPrice(1.0d);
		product.setName("SomeProduct");
		return Single.just(product);
	}

	@Override
	public Single<Product> add(Product obj) {
		Product product = new Product();
		product.setSellerId("NewSeller");
		product.setPrice(2.0d);
		product.setName("NewProduct");
		return Single.just(product);
	}

	@Override
	public Single<Product> edit(Product obj) {
		Product product = new Product();
		product.setSellerId("EditedSeller");
		product.setPrice(3.0d);
		product.setName("EditedProduct");
		return Single.just(product);
	}

	@Override
	public Single<Boolean> delete(Product object) {
		return Single.just(true);
	}

	@Override
	public Observable<Product> getAll() {
		List<Product> productList = new LinkedList<>();
		Product product = new Product();
		product.setSellerId("Someone");
		product.setPrice(1.0d);
		product.setName("SomeProduct");
		productList.add(product);
		return Observable.from(productList);
	}

}
