package edu.saby.msec.catalogserver.model;

import org.springframework.data.annotation.Id;

/**
 * @author Abhishek Sarkar
 *
 */
public abstract class AbstractEntityBase {
	
	@Id
    private String id;
	
	public AbstractEntityBase() {}
	
	public AbstractEntityBase(String id) {
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
	
}
