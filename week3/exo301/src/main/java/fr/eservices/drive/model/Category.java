package fr.eservices.drive.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;

	// Properties
	/**
	 * Category ID 	Unique identifier for this category.
	 */
	@Id
	private int id;	
	/**
	 * Category name
	 */
	private String name;	
	/**
	 * Order index that will allow to sort products on an order given by the administrator 
	 */
	private int orderIdx;

	@ManyToMany(mappedBy="categories")
	private List<Article> articles = new ArrayList<>();
	
	// ctors
	public Category() {
		super();		
	}
	
	public Category(String name, int orderIdx) {
		this.name = name;
		this.orderIdx = orderIdx;
	}

	
	// Getters and Setters
	public int getId() {
		return id;
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

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}	
	
    // Category Representation:
    @Override
    public String toString() {
        return String.format("(%d, %s, %d)", this.id, this.name, this.orderIdx);
    }
}