import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.AirlineCoverage;
import flights.Airline;
import flights.Airport;
import flights.Route;

/**
 * Reprise de App.java afin de réecrire toutes les requête JPQL à l'aide de l'API Criteria 
 *
 */
public class AppCriteria {
	private static Logger log = LoggerFactory.getLogger( AppCriteria.class );
	
	public static void main(String argv[]) {
		System.out.println("Running AppCriteria ...");
		
		log.debug("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		
		log.debug("Search for French Airlines");
		CriteriaBuilder cb = em.getCriteriaBuilder();	
		
		CriteriaQuery<Airline> cq = cb.createQuery(Airline.class);
		// Clause FROM starts from the root node of the tree Airline objects
		Root<Airline> al = cq.from(Airline.class);
		// Déclaration et typage du paramètre
		ParameterExpression<String> country = cb.parameter(String.class);
	 	cq.select(al).where( cb.equal(al.get("country"), country) );
	 	
		TypedQuery<Airline> tq = em.createQuery(cq);
	 	tq.setParameter(country, "France");
		List<Airline> airlines = tq.getResultList();
		
		showAirlines(airlines);
				
		
		log.debug("Search for French Airlines going to Miami");
		CriteriaQuery<Airline> cq2 = cb.createQuery(Airline.class);
		Root<Route> rt = cq2.from(Route.class);
		Join<Route, Airline> al2 = rt.join("airline");
		Join<Route, Airport> ap = rt.join("destination");
		
		cq2
			.select( rt.get("airline") ).distinct(true)
			.where(
					cb.and(
							cb.equal(al2.get("country"), "France"),
							cb.equal(ap.get("city"), "Miami")
						   )
			 );

		airlines = em.createQuery(cq2)
					  .getResultList();

		showAirlines(airlines);
	
		
		log.debug("Search Number of airlines from CDG");
		CriteriaQuery<Long> cq1 = cb.createQuery(Long.class);
		
		Root<Route> rt1 = cq1.from(Route.class);
		Join<Route, Airport> ap1 = rt1.join("source");
		
		cq1
			.select( cb.countDistinct(rt1.get("airline")) )
			.where( cb.equal( ap1.get("iata"), "CDG") );
		
		Long count = em.createQuery(cq1)
					   .getSingleResult();
		
		System.out.println(count + " airlines start from CDG");
		

		log.debug("Search Best airlines with number of dest airports to USA from CDG, ordered by 'coverage'");
		CriteriaQuery<AirlineCoverage> cq3 = cb.createQuery(AirlineCoverage.class);
		
		Root<Route> rt3 = cq3.from(Route.class);
		Join<Route, Airport> apDest = rt3.join("destination");
		Join<Route, Airport> apSource = rt3.join("source");
		Join<Route, Airline> al3 = rt3.join("airline");
		
		javax.persistence.criteria.Expression<Long> cnt = cb.count(rt3.get("destination"));
		
		cq3.select( cb.construct( AirlineCoverage.class, 
				                  cnt,
				                  apDest.get("country"),
				                  al3.get("name") ) )
		    .where(
		    		cb.and(
		    				cb.equal(apDest.get("country"), "United States"),
		    				cb.equal(apSource.get("iata"), "CDG")
		    		)
		   	 )
		    .groupBy(
	                  apDest.get("country"),
	                  al3.get("name")
		   	 )
		    .orderBy(
		    		cb.desc(cnt)
		     );
		
		List<AirlineCoverage> airlineCoverages =  em.createQuery(cq3).getResultList();
		
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
							airlineCoverage.getAirline()
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