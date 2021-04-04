package edu.saby.msec.catalog.model;

import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.saby.msec.infra.model.AbstractEntityBase;

/**
 * @author Soumya Banerjee & Abhishek Sarkar
 *
 */
@Document(collection = "Product")
public class Product extends AbstractEntityBase {
	
	@Indexed
	private String name;
	private String category;
	private String description;
	
	/**
	 * {@link Map} of Key - Seller ID and Value - Sell Price assigned for the product by the Seller
	 */
	private Map<String, Double> sellerOfferings;
	
	public Product() {}
	
	public Product(String id) {
        super(id);
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the sellerOfferings
	 */
	public Map<String, Double> getSellerOfferings() {
		return sellerOfferings;
	}

	/**
	 * @param sellerOfferings the sellerOfferings to set
	 */
	public void setSellerOfferings(Map<String, Double> sellerOfferings) {
		this.sellerOfferings = sellerOfferings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [name=").append(name).append(", category=").append(category).append(", description=")
				.append(description).append(", sellerOfferings=").append(sellerOfferings).append(", getId()=")
				.append(getId()).append("]");
		return builder.toString();
	}
	
}
