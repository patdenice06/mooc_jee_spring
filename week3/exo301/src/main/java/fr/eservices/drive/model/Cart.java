package fr.eservices.drive.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CART")
public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdOn;
	
	/**
	 * One cart contain many articles
	 */
	@OneToMany
	private List<Article> articles;
	
	/**
	 * Several carts for one customer
	 */
	@ManyToOne
	private Customer customer;

	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
