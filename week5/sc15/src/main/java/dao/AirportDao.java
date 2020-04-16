package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import flights.Airport;

@Component
public class AirportDao {
	
	@PersistenceContext
	EntityManager em;
	
	// TODO : should add a transaction manager

	
	public List<String> existingCountries() {
		return 
			em.createQuery( 
				"Select distinct country From Airport "
				+ "Order by country",
				String.class
			)
			.getResultList();
	}
	
	public List<Airport> byCountry(String country) {
		return 
			em.createQuery( 
				"From Airport where country = :country "
				+ "Order by city, iata",
				Airport.class
			)
			.setParameter("country", country)
			.getResultList();
	}

	public Airport find(int id) {
		return em.find(Airport.class, id);
	}
	
	public void update(Airport airport) {
		em.merge(airport);
	}

}
