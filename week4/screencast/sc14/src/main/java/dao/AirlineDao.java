package dao;

import java.util.List;

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

	/**
	 * 
	 * @return All airlines
	 */
	public List<Airline> list() {
		List<Airline> airlines = em.createQuery(
				"Select a From Airline as a",
				Airline.class
				)
				.getResultList();
		
		return airlines;
	}
	
	
}
