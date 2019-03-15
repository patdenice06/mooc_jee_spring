package com.tuto.onetoone.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock implements java.io.Serializable{	
	private static final long serialVersionUID = 8207326100836617687L;
	
	private Integer stockId;
	private String stockCode;
	private String stockName;
	private StockDetail stockDetail;
	
	// ctors
	public Stock() {
	}

	public Stock(String stockCode, String stockName) {
		this.stockCode = stockCode;
		this.stockName = stockName;
	}

	public Stock(String stockCode, String stockName, StockDetail stockDetail) {
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.stockDetail = stockDetail;
	}
	

	// Getters/Setters
	@Id
	@GeneratedValue
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	public Integer getStockId() {
		return this.stockId;
	}	


	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	
	@Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
	public String getStockCode() {
		return this.stockCode;
	}
	
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	@Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20)
	public String getStockName() {
		return this.stockName;
	}
	
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
		
	@OneToOne(mappedBy="stock")
	public StockDetail getStockDetail() {
		return this.stockDetail;
	}	
	
	public void setStockDetail(StockDetail stockDetail) {
		this.stockDetail = stockDetail;
	}	
	
}
