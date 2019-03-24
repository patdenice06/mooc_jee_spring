package fr.eservices.drive.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY", catalog = "TEST_DB", schema = "PUBLIC")  
public class Category implements Serializable{
	private static final long serialVersionUID = -8647821521022617248L;

	// Properties
	/**
	 * Category ID 	Unique identifier for this category.
	 */
	@Id
	@Column(name="ID")
	private int id;
	
	/**
	 * Category name
	 */
	@Column(name="NAME")
	private String name;
	
	/**
	 * Order index that will allow to sort products on an order given by the administrator 
	 */
	@Column(name="ORDERIDX")
	private int orderIdx;

	@ManyToMany(mappedBy = "categories")
	private List<Article> articles;
	
	
	// ctors
	public Category() {}
	
	public Category(String name, int orderIdx) {
		super();
		this.name = name;
		this.orderIdx = orderIdx;
	}

	
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

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
}