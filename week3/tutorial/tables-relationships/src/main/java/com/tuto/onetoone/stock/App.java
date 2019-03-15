package com.tuto.onetoone.stock;

import java.io.File;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {

	public static void main(String[] args) {

		System.out.println("Running App ...");
		
		System.out.println("Delete mkyongdb ...");
		// quickest way to delete tables and data ...
		new File("./mkyongdb.mv.db").delete();
		
		System.out.println("Create persistence manager");		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oneToOne");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Hibernate one to one (Annotation)");
		System.out.println("Create entity Stock");
		Stock stock = new Stock();

		stock.setStockCode("7052");
		stock.setStockName("PADINI");

		System.out.println("Create entity StockDetail");
		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("PADINI Holding Malaysia");
		stockDetail.setCompDesc("one stop shopping");
		stockDetail.setRemark("vinci vinci");
		stockDetail.setListedDate(new Date());

		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);
		
		System.out.println("Persist entities in a transaction");
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(stock);
		em.persist(stockDetail);
		
		transaction.commit();
		
		System.out.println("Close Entity Manager");
		em.close();
		emf.close();		
		
	}

}
