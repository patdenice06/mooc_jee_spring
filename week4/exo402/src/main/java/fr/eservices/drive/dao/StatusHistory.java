package fr.eservices.drive.dao;

import java.util.Date;

import fr.eservices.drive.model.TimeObject;

public class StatusHistory {
	
	private Status status;
	private Date statusDate;
	private TimeObject timeObject;
	
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
	public void setStatusDate(TimeObject t) {
		this.timeObject = t;
		
	}
	
	
	
	
	
	

}
