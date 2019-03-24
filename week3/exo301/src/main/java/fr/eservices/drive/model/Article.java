package fr.eservices.drive.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ARTICLE", catalog = "TEST_DB", schema = "PUBLIC") 
// @Inheritance(strategy=InheritanceType.JOINED)	// Good strategy choice ?
public class Article implements Serializable{
	private static final long serialVersionUID = 8897019909586915538L;
	
	// Properties
	@Column(name="DTYPE")
	private String dtype;
	/**
	 * Article ID 	Unique identifier for this article.
	 */
	@Id
	@Column(name="ID")
	private int id;
	
	/**
	 * Barcode coded with 13 characters  
	 */
	@Column(name="EAN")
	private String ean13;
	
	/**
	 * Name of the article
	 */
	@Column(name="NAME")
	private String name;

	/**
	 * Price is in cents representing
	 */
	@Column(name="PRICE")
	private int price;
	
	/**
	 * VAT number (0.20 = 20%)
	 */
	@Column(name="VAT")
	private double vat;
	
	@Column(name="IMG")
	private String img;
	
	/**
	 * Articles may contain many categories
	 */
	@ManyToMany
	@JoinTable(name = "ARTICLE_CATEGORY",
		joinColumns = { @JoinColumn(referencedColumnName = "ID") },
		inverseJoinColumns = { @JoinColumn(referencedColumnName = "ID") })
	private List<Category> categories;

	
	// ctors
	public Article() {
		super();
	}
	
		
	public Article(String dtype, int id, String ean13, String name, int price, double vat, String img,
			List<Category> categories) {
		this.dtype = dtype;
		this.id = id;
		this.ean13 = ean13;
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


	public void setId(int id) {
		this.id = id;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
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

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
