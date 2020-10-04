package edu.saby.msec.catalogserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.saby.msec.catalogserver.model.Product;
import edu.saby.msec.infra.controller.GenericEntityController;

/**
 * @author Soumya Banerjee & Abhishek Sarkar
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController extends GenericEntityController<Product> {

}
