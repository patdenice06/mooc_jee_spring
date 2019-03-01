package fr.eservices.drive.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

	/**
	 * Category ID 	Unique identifier for this category.
	 */
	@Id
	private int id;
	
	/**
	 * Name of the this category
	 */
	private String name;
	
	/**
	 * Order index that will allow to sort products on an order given by the administrator 
	 */
	private int orderIdx;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderIdx() {
		return orderIdx;
	}

	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
	}
	
}