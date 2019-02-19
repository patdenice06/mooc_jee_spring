import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flights.Airline;

public class App {
	private static Logger log = LoggerFactory.getLogger( App.class );
	
	public static void main(String argv[]) {
		System.out.println("Running App ...");
		
		log.debug("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		
		log.debug("Search for French Airlines");
		List<Airline> airlines = em.createQuery("from Airline as al where al.country = :country", Airline.class)
		  .setParameter("country", "France")
		  .getResultList();
		
		showAirlines(airlines);
		
		
		log.debug("Search for French Airlines going to Miami");
		airlines = em.createQuery(
				" SELECT DISTINCT rt.airline FROM Route AS rt "
				+" JOIN rt.airline AS al "
				+" JOIN rt.destination AS dst "
				+" WHERE al.country = :country AND dst.city = :city", Airline.class)
				.setParameter("country", "France")
				.setParameter("city", "Miami")
				.getResultList();
		
		showAirlines(airlines);
		
		// Another JPA code format to execute the same request
//		TypedQuery<Airline> query= em.createQuery(
//				" SELECT DISTINCT rt.airline FROM Route AS rt "
//				+" JOIN rt.airline AS al "
//				+" JOIN rt.destination AS dst "
//				+" WHERE al.country = :country AND dst.city = :city", Airline.class);
//		query.setParameter("country", "France");
//		query.setParameter("city", "Miami");
//		airlines = query.getResultList();
//				
//		showAirlines(airlines);
				
		
		log.debug("Search Number of airlines from CDG");
		
		log.debug("Search Best airlines with number of dest airports to USA from CDG, ordered by 'coverage'");
		
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