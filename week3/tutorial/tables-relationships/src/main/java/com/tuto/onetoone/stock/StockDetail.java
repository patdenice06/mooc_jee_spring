package com.tuto.onetoone.stock;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "STOCK_DETAIL")
public class StockDetail implements java.io.Serializable{
	private static final long serialVersionUID = 5299581086765205385L;
	
	private Integer stockDetailId;	// PK
	private String compName;
	private String compDesc;
	private String remark;
	private Date listedDate;
	private Stock stock;	
	
	// ctors
	public StockDetail() {
	}

	public StockDetail(Stock stock, String compName, String compDesc,
			String remark, Date listedDate) {
		this.stock = stock;
		this.compName = compName;
		this.compDesc = compDesc;
		this.remark = remark;
		this.listedDate = listedDate;
	}	
	

	// Getters/Setters
	@Id
	@GeneratedValue
	@Column(name = "STOCKDETAIL_ID", unique = true, nullable = false)
	public Integer getStockDetailId() {
		return stockDetailId;
	}
	
	
	public void setStockDetailId(Integer stockId) {
		this.stockDetailId = stockId;
	}

	@OneToOne
	@JoinColumn(name="stockId")		// FK
	public Stock getStock() {
		return stock;
	}
		
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
	@Column(name = "COMP_NAME", nullable = false, length = 100)	
	public String getCompName() {
		return compName;
	}
	
	
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	@Column(name = "COMP_DESC", nullable = true, length = 100)		
	public String getCompDesc() {
		return compDesc;
	}
	
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	
	@Column(name = "REMARK", nullable = true)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LISTED_DATE", nullable = false, length = 10)
	public Date getListedDate() {
		return listedDate;
	}
		
	public void setListedDate(Date listedDate) {
		this.listedDate = listedDate;
	}
}
