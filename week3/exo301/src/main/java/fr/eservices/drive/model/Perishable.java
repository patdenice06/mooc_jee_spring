package fr.eservices.drive.model;

import java.io.Serializable;
import  java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PERISHABLE")
public class Perishable extends Article implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * Best before date for perishable articles
	 */
	@Temporal(value = TemporalType.DATE)
	private Date bestBefore;
	
	/**
	 * Text representing frech products delivery 
	 */
	private String lot;

	// ctors
    /** Creates a new instance of Perishable */
    public Perishable() { 
    }

    
    
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
	
	// Methods
	@Override
	public String toString() {		
		return String.format( "(%s, %s)", this.bestBefore.toString(), this.lot );		
	}	
}
