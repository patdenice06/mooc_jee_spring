package fr.eservices.drive.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.drive.model.StatusHistoryEntity;
import fr.eservices.drive.web.HistorySource;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * HistorySource implementation using in a JSON POST Request handled by the REST controller 
 * <B>RestHistoryController</B>   
 */

@Component
@Qualifier("jpa")
public class HistorySourceJpaImpl implements HistorySource{

	@Autowired
	EntityManager em;
	@Autowired
	EntityTransaction tx;
	
	public HistorySourceJpaImpl(){}
	
	
	@Override
	public List<StatusHistory> orderHistory(int orderId) {
		// Return status from table STATUS_HISTORY
		System.out.println("HistorySourceJpaImpl.orderHistory()"+ "\t orderId="+ orderId);
		
		List<StatusHistory> statusHistories = new ArrayList<StatusHistory>( Arrays.asList());
		StatusHistoryEntity statusHistoryEntity = new StatusHistoryEntity();
		
		try {
			tx.begin();
				statusHistoryEntity = (StatusHistoryEntity) em.find(StatusHistoryEntity.class, orderId);		
			tx.commit();			
		} catch (Exception e) {
			tx.rollback();
		}		
		
		StatusHistory statusHistory = new StatusHistory();
		statusHistory.setOrderId(orderId);
		statusHistory.setStatus( Status.valueOf(statusHistoryEntity.getStatus()) );
		statusHistory.setStatusDate( statusHistoryEntity.getStatusDate() );
		statusHistories.add(0, statusHistory);
		
		return statusHistories;
	}


	@Override
	public void addHistoryStatus(int orderId, StatusHistory statusHistory) throws DataException {
		// Create a Status History in table STATUS_HISTORY
		System.out.println("HistorySourceJpaImpl.addHistoryStatus(). orderID = "+ orderId);
		
		StatusHistoryEntity statusEntity = new StatusHistoryEntity();
		statusEntity.setOrderId( orderId );
		statusEntity.setStatus( statusHistory.getStatus().name() );
		statusEntity.setStatusDate( statusHistory.getStatusDate() );
		save(statusEntity);
	}
	

	@Override
	public List<StatusHistoryEntity> orderAllHistory(int orderId) throws DataException {
		// Get all history status from a given orderId
		System.out.println("HistorySourceJpaImpl.orderAllHistoryl()"+ "\t orderId= "+ orderId);
		
		  TypedQuery<StatusHistoryEntity> query =
			      em.createQuery("SELECT s FROM StatusHistoryEntity AS s WHERE s.orderId = :p_orderId", StatusHistoryEntity.class);
		  query.setParameter("p_orderId", orderId);
		return query.getResultList();
	}	

	
	@Override
	public void addHistoryAllStatusl(int orderId, List<StatusHistory> histories) throws DataException {
		// Create a list of Status History in table STATUS_HISTORY
		System.out.println("HistorySourceJpaImpl.addHistoryAllStatusl()");
		
		StatusHistoryEntity statusEntity = null;
		for (StatusHistory history : histories) {
			statusEntity = new StatusHistoryEntity();
			statusEntity.setOrderId(history.getOrderId());
			statusEntity.setStatus( history.getStatus().name() );
			statusEntity.setStatusDate( history.getStatusDate() );
			
			//DEBUG
			System.out.println( history.toString() );
			
			save( statusEntity );
		}
	}
	
	
	private void save(StatusHistoryEntity statusEntity) {
		System.out.println("HistorySourceJpaImpl.save()");
		try {
			tx.begin();
				em.persist( statusEntity );		
			tx.commit();			
		} catch (Exception e) {
			tx.rollback();
		}		
	}





}
