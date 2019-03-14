package com.tuto.onetomany.company;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Employee implements Serializable{
	private static final long serialVersionUID = 8645483517504417182L;

	@Id
	@GeneratedValue
	private Long employeeId;	// PK	
	private String employeeName;	
	@OneToOne(mappedBy="employee")
	private Address address;
	
	
	// Getters/Setters
	public Long getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	
}
