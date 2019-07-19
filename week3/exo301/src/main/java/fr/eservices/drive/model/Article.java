package fr.eservices.drive.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICLE")
// DTYPE is the default discriminator column name which values are "Perishable" or "Product"
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Article ID 	Unique identifier for this article.
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private int id;
		
	/**
	 * Barcode coded with 13 characters  
	 */
    @Column(name = "EAN")
	private String ean;
    
	/**
	 * Name of the article
	 */
    @Column(name = "NAME")    
	private String name;

	/**
	 * Price is in cents representing
	 */
    @Column(name = "PRICE")    
	private int price;	
	
	/**
	 * VAT number (0.20 = 20%)
	 */
    @Column(name = "VAT")    
	private double vat;	
	
	/**
	 * Path to the gif image
	 */
    @Column(name = "IMG")    
    private String img;
    
	
	/**
	 * One article may belong to many categories
	 */
	@ManyToMany
	@JoinTable(name="ARTICLE_CATEGORY",
       joinColumns=
           @JoinColumn(name="ARTICLE_ID", referencedColumnName="ID"),
       inverseJoinColumns=
           @JoinColumn(name="CATEGORIES_ID", referencedColumnName="ID")
       )
	private List<Category> categories = new ArrayList<>();

	
	// ctors
	public Article() {
		super();
	}
	
		
	public Article(int id, String ean, String name, int price, double vat, String img, List<Category> categories) {
		this.id = id;
		this.ean = ean;
		this.name = name;
		this.price = price;
		this.vat = vat;
		this.img = img;
		this.categories = categories;
	}



	// Getters and Setters
	public int getId() {
		return id;
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
	
	// Methods
	@Override
	public String toString() {		
		return String.format("(%d, %s, %s, %d, %s, %s)", this.id, this.ean, this.name, this.price, new Double(this.vat).toString(), this.img );		
	}
}
