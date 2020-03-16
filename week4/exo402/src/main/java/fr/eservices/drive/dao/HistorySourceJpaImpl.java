package fr.eservices.drive.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
		System.out.println("HistorySourceJpaImpl.orderHistory()");
		
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
		statusEntity.setStatus( statusHistory.getStatus().name() );
		statusEntity.setStatusDate( statusHistory.getStatusDate() );
		save(statusEntity);
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
