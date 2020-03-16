package fr.eservices.drive.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.DatatypeConverter;

import fr.eservices.drive.model.StatusHistoryEntity;
import fr.eservices.drive.model.TimeObject;

/**
 * DAO JPA without Spring context 
 */
public class HistorySourceJpa {
	
	private EntityManager em;
	private EntityTransaction tx;
	
	private List<StatusHistory> status;
	
	
	public HistorySourceJpa() {
		status = new ArrayList<StatusHistory>( Arrays.asList(
			createStatus( "ORDERED",          "2017-11-28T10:00:00Z" ),
			createStatus( "READY_TO_DELIVER", "2017-11-28T10:00:00Z" )
		));
	}

	SimpleDateFormat sdf = new SimpleDateFormat("");
	private StatusHistory createStatus(String statusName, String dateTime) {
		StatusHistory status = new StatusHistory();
		status.setStatus( Status.valueOf(statusName) );
		status.setStatusDate( DatatypeConverter.parseDate(dateTime).getTime() );
		TimeObject t = new TimeObject();
		Date now = new Date();
		t.day = DateFormat.getDateInstance(DateFormat.SHORT).format(now);
		t.time = DateFormat.getTimeInstance(DateFormat.SHORT).format(now);
		t.locale = Locale.getDefault().toString();
		t.timestamp = (long) (now.getTime() / 1000);
		status.setTimeObject(t);
		return status;
	}
	
	void save(StatusHistoryEntity statusEntity) {
		try {
			tx.begin();
				em.persist( statusEntity );		
			tx.commit();			
		} catch (Exception e) {
			tx.rollback();
		}		
	}
	
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		HistorySourceJpa dao = new HistorySourceJpa();
		dao.em = emf.createEntityManager();
		dao.tx = dao.em.getTransaction();				
		
		StatusHistoryEntity statusEntity = null;
		String s = null;
		Date d = null;
		for (int i = 0; i < dao.status.size(); i++) {
			statusEntity = new StatusHistoryEntity();
			s = dao.status.get(i).getStatus().toString();
			d = dao.status.get(i).getStatusDate();
			statusEntity.setStatus(s);
			statusEntity.setStatusDate(d);
			dao.save(statusEntity);
		}
		
		emf.close();
	}

}
