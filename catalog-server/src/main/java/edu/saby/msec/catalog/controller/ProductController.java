package edu.saby.msec.catalog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.saby.msec.catalog.model.Product;
import edu.saby.msec.infra.controller.GenericEntityController;

/**
 * @author Soumya Banerjee & Abhishek Sarkar
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController extends GenericEntityController<Product> {

}
