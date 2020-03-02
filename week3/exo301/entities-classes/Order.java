package fr.eservices.drive.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Order {

	@Id
	@GeneratedValue
	private int id;	
	@Temporal(value = TemporalType.DATE)
	private Date createdOn;	
	@Temporal(value = TemporalType.DATE)
	private Date deliveryDate;	
	/**
	 * Amount is in cents representing
	 */
	private int amount;	
	@ManyToMany
	private List<Article >articles;	
	@OneToMany
	private List<StatusHistory> history;	
    @ManyToOne(optional=false)
//    @JoinColumn(name="CUSTOMER_ID", nullable=false, updatable=false)
    @JoinColumn(name="ID", nullable=false, updatable=false)
	private Customer customer;
	private Status currentStatus;
	
	
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
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<StatusHistory> getHistory() {
		return history;
	}

	public void setHistory(List<StatusHistory> history) {
		this.history = history;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	
}
	
