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
		
				
		
		log.debug("Search Number of airlines from CDG");
		Long count = em.createQuery(
				"select count(distinct rt.airline) "
				+"from Route as rt "
				+"join rt.source as ap "
				+"where ap.iata = :code ",
				Long.class
		)
		.setParameter("code", "CDG")
		.getSingleResult();
		System.out.println(count + " airlines start from CDG");
		
		
		log.debug("Search Best airlines with number of dest airports to USA from CDG, ordered by 'coverage'");
		List<AirlineCoverage> airlineCoverages = em.createQuery(
				"Select new data.AirlineCoverage( count(rt.destination), apD.country, al.name ) "
			   +"from Route as rt "
			   +"join rt.destination as apD "
			   +"join rt.source as apS "
			   +"join rt.airline as al "
			   +"where apD.country = :country and apS.iata = :code "
			   +"Group by apD.country, al.name "	// On doit placer les colonnes du "Select" qui ne sont pas des fonctions d'agrégation  
			   +"Order by count(rt.destination) desc ",
			   AirlineCoverage.class
		)
		.setParameter("country", "United States")
		.setParameter("code", "CDG")
		.getResultList();
		
		System.out.println( "Best airlines with number of dest airports to USA from CDG, ordered by 'coverage'" );		
		System.out.println(
				String.format("%3s	|	%-20s	|	%-30s",
						"Routes",
						"Country",
						"Airline"
				)
			);
		
		for (AirlineCoverage airlineCoverage : airlineCoverages) {
			System.out.println(
					String.format("%3d	|	%-20s	|	%-30s",
							airlineCoverage.getNbRoutes(),
							airlineCoverage.getCountry(),
							airlineCoverage.getAirlineName()
					)
				);
		}
		
		
		
		
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