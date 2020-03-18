package fr.eservices.drive.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STATUS_HISTORY")
public class StatusHistoryEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int orderId;
	@Temporal(value = TemporalType.DATE)
	private Date statusDate;	
	private String status;

	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String s) {
		this.status = s;
	}
	
	
}
