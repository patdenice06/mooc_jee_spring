import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.AirlineCoverage;
import flights.Airline;

public class App {
	private static Logger log = LoggerFactory.getLogger( App.class );
	
	public static void main(String argv[]) {
		System.out.println("Running App ...");
		
		log.debug("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		
		List<Airline> airlines;
		log.debug("Search for French Airlines");
		System.out.println("Search for French Airlines");
		airlines = em.createQuery("from Airline as al where al.country = :country", Airline.class)
		.setParameter("country", "France")
		.getResultList();
		
		showAirlines( airlines );
		
		
		log.debug("Search for French Airlines going to Miami");
		System.out.println("Search for French Airlines going to Miami");
		airlines = em.createQuery(
			"select distinct rt.routeId.airline from Route as rt "
			+ " join rt.routeId.airline as al "
			+ " join rt.routeId.destination as dst "
			+ " where al.country = :country and dst.city = :city", Airline.class)
		.setParameter("country", "France")
		.setParameter("city", "Miami")
		.getResultList();
		
		showAirlines(airlines);

		log.debug("Search Number of airlines from CDG");
		System.out.println("Search Number of airlines from CDG");
		long count = em.createQuery(
			"Select count(distinct rt.routeId.airline) "
			+ "from Route as rt "
			+ "join rt.routeId.source as ap "
			+ "where ap.iata = :code",
			Long.class
		)
		.setParameter("code", "CDG")
		.getSingleResult();
		System.out.println(count + " airlines starts at CDG");
		
		log.debug("Search Best airlines with number of dest airports to USA from CDG, ordered by 'coverage'");
		System.out.println("Search Best airlines with number of dest airports to USA from CDG, ordered by 'coverage'");
		
		List<AirlineCoverage> res = em.createQuery(
			"Select new data.AirlineCoverage( count(rt.routeId.destination), apD.country, al.name ) "
			+ "From Route as rt "
			+ "Join rt.routeId.destination as apD "
			+ "Join rt.routeId.source as apS "
			+ "Join rt.routeId.airline as al "
			+ "Where apD.country = :country "
			+ "  and apS.iata = :code "
			+ "Group by apD.country, al.name "
			+ "Order by count(rt.routeId.destination) desc",
			AirlineCoverage.class
		)
				.setParameter("country", "United States")
				.setParameter("code", "CDG")
				.getResultList();
		
		for ( AirlineCoverage coverage : res ) {
			System.out.println(
				String.format("%3d | %-20s | %-30s", coverage.getNbRoutes(), coverage.getCountry(), coverage.getAirlineName() )
			);
		}
		
		
		log.debug("Close Entity Manager");
		em.close();
		emf.close();

	}

	private static void showAirlines(List<Airline> airlines) {
		System.out.println(
				String.format("%5s | %3s | %-30s ", 
					"Id",
					"ICAO",
					"Name"
				)
			);
			for ( Airline airline : airlines ) {
				System.out.println(
					String.format("%5d | %3s | %-30s ", 
						airline.getAirlineId(),
						airline.getIcao(),
						airline.getName()
					)
				);
			}
	}
}