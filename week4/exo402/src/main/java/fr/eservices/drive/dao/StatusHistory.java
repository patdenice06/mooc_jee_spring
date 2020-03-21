package fr.eservices.drive.dao;

import java.io.Serializable;
import java.util.Date;

import fr.eservices.drive.model.TimeObject;

public class StatusHistory implements Serializable{
	private static final long serialVersionUID = -1072811194225726450L;
	
	private int orderId;
	private Status status;
	private Date statusDate;
	private TimeObject timeObject;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public TimeObject getTimeObject() {
		return timeObject;
	}
	public void setTimeObject(TimeObject t) {
		this.timeObject = t;		
	}
	
    @Override
    public String toString() {
        return "StatusHistory [orderId=" + this.orderId + ", status=" + this.status.name() + ", statusDate="+  this.statusDate.toLocaleString()  +"]";
    }
	
	
	
	
	

}
