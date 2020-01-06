package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import flights.Airline;

// set as component
@Component
public class AirlineDao {

	// inject entity manager
	@PersistenceContext
	EntityManager em;
	
	public Airline find(int id) {
		return em.find(Airline.class, id);
	}
	
	
}
