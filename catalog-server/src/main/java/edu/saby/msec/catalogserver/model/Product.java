package edu.saby.msec.catalogserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Soumya Banerjee & Abhishek Sarkar
 *
 */
@Document(collection = "Product")
public class Product {
	
	@Id
    private String id;
	
	private String sellerId;
	private String name;
	private Double price;
	
	public Product() {}
	
	public Product(String id) {
        this.id = id;
    }
	
	/**
	 * 
	 * @return the id of the product
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id the product id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the sellerId
	 */
	public String getSellerId() {
		return sellerId;
	}
	
	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", sellerId=" + sellerId + ", name=" + name + ", price=" + price + "]";
	}
	
}
