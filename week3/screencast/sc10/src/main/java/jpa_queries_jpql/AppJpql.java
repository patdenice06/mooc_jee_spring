package jpa_queries_jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flights.Airline;

public class AppJpql {

	private static Logger log = LoggerFactory.getLogger( AppJpql.class );

	public static void main(String argv[]) {
		System.out.println("Running App ...");
		
		log.debug("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Airline> query = null;
		List<Airline> airlines = null;
		
		// *** Some examples from https://www.objectdb.com/java/jpa/query/jpql/select ***//
//		log.debug("Select all objects from table AIRLINE");
		
		// SQL: SELECT * FROM AIRLINE
		// JPQL ...
//		query = em.createQuery("SELECT c FROM Airline c", Airline.class);
//		airlines = query.getResultList();			 
//		showAirlines(airlines);
		
//		log.debug("Select all French Airlines");
//		// SQL: SELECT * FROM AIRLINE  WHERE COUNTRY='France'
//		// JPQL ...
//		query = em.createQuery("SELECT c FROM Airline c "
//							  +"WHERE c.country = :country", Airline.class);
//		query.setParameter("country", "France");
//		airlines = query.getResultList();
//		showAirlines(airlines);
		
		
		log.debug("Search for French Airlines going to Miami: version with JOIN");
		// TypedQuery JPA
		query= em.createQuery(
				" SELECT DISTINCT rt.airline FROM Route AS rt "
				+" JOIN rt.airline AS al "
				+" JOIN rt.destination AS dst "
				+" WHERE al.country = :country AND dst.city = :city", Airline.class);
		query.setParameter("country", "France");
		query.setParameter("city", "Miami");
		airlines = query.getResultList();
				
		showAirlines(airlines);

		log.debug("Search for French Airlines going to Miami: version without JOIN");
		query = em.createQuery("select distinct rt.airline "
					        + "from Route as rt "
					        + "where rt.airline.country = :country and "
					        + "rt.destination.city = :city", Airline.class);
		query.setParameter("country", "France");
		query.setParameter("city", "Miami");
		airlines = query.getResultList();
		
		showAirlines(airlines);
		

		log.debug("Search Number of airlines from CDG");
		long countFromCDG = em.createQuery("select count(distinct rt.airline) "
							  +"from Route as rt "
							  +"where rt.source.iata = :iataCode",
							  Long.class)
							.setParameter("iataCode", "CDG")
							.getSingleResult();
		System.out.printf("There are %d airlines departing from %s.%n", countFromCDG, "CDG");

		
		log.debug("Close Entity Manager");
		em.close();
		emf.close();
	}
	
	private static void showAirlines(List<Airline> airlines) {
		System.out.println(
			String.format("%5s	|	%3s	|	%-30s",
					"Id",
					"ICAO",
					"Name"
			)
		);
		
		for (Airline airline : airlines) {
			System.out.println(
				String.format("%5s	|	%3s	|	%-30s",
						airline.getId(),
						airline.getIcao(),
						airline.getName()
				)
			);
		}
	}	
}
