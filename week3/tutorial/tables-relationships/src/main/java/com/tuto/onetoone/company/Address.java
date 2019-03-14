package com.tuto.onetoone.company;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address implements Serializable{
	private static final long serialVersionUID = -5565791383813258308L;
	
	@Id
	@GeneratedValue
	private Long adressId;		// PK
	
	private String streetName;
	private String zipCode;
	
	@OneToOne
	@JoinColumn(name="employeeId")	// FK
	private Employee employee;

	// Getters/Setters
	public Long getAdressId() {
		return adressId;
	}

	public void setAdressId(Long adressId) {
		this.adressId = adressId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}
