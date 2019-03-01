package fr.eservices.drive.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Article {

	@Id
	/**
	 * Article ID 	Unique identifier for this article.
	 */
	private int id;
	
	/**
	 * Barcode coded with 13 characters  
	 */
	private String ean;
	
	/**
	 * Price is in cents representing
	 */
	private int price;
	
	/**
	 * VAT number (0.20 = 20%)
	 */
	private double vat;
	
	/**
	 * Name of the article
	 */
	private String name;
	
	/**
	 * Articles may contain many categories
	 */
	@ManyToMany
	private List<Category> categories;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
