package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import flights.Route;

// expose as component
@Component
public class RouteDao {

	// inject EntityManager
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Find routes by source and destination countries.
	 * By default source country is France
	 * @param fromCountry Source country
	 * @param toCountry	Destination country
	 * @return Routes
	 */
	public List<Route> findRoutesByCountries(String fromCountry, String toCountry) {		
		// build a request and return routes
		List<Route> route = em.createQuery(
				"Select rt from Route as rt "
			   +"Join rt.routeId.source as src "
			   +"Join rt.routeId.destination as dst "
			   +"where src.country =:from "				
			   +"and dst.country =:to",
			   Route.class
				)
			.setParameter("from", fromCountry)
			.setParameter("to", toCountry)
			.getResultList();
		
		return route;
	}

	
	/**
	 * Find route by its ID
	 * @param id	route ID
	 * @return A route
	 */
	public Route findRouteById(Long id) {
		Route route = em.createQuery("SELECT r FROM Route r WHERE r.id = :id", Route.class)
				.setParameter("id", id)
				.getSingleResult();
		
		return route;
	}

	/**
	 * Find routes by the three codes parameters 
	 * @param airlineCode	
	 * @param sourceCode
	 * @param destinationCode
	 * @return Routes
	 */
	public Route findRouteByThreeCodes(String airlineCode, String sourceCode, String destinationCode) {
		Route route = em.createQuery("SELECT r FROM Route r WHERE r.airlineCode = :air and r.sourceCode = :src and r.destinationCode = :dst", Route.class)
						.setParameter("air", airlineCode)
						.setParameter("src", sourceCode)
						.setParameter("dst", destinationCode)
						.getSingleResult();
		return route;
	}
	
}
