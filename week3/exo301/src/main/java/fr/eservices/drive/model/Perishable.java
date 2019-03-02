package fr.eservices.drive.model;

import  java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Perishable extends Article{
	/**
	 * Best before date for perishable articles
	 */
	@Temporal(value = TemporalType.DATE)
	private Date bestBefore;
	
	/**
	 * Text representing frech products delivery 
	 */
	private String lot;


	// Getters and Setters
	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;		
	}

	public Date getBestBefore() {
		return bestBefore;
	}

	public void setBestBefore(Date bestBefore) {
		this.bestBefore = bestBefore;
	}
}
