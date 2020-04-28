package dao;

import java.util.ArrayList;
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
	
	public List<Route> findRoutesByCountries(String fromCountry, String toCountry) {
		List<Route> res = new ArrayList<>();
		
		// TODO : build a request an return routes

		
		return res;
	}

}
