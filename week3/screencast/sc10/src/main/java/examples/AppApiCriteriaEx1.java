package examples;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppApiCriteriaEx1 {

	private static Logger log = LoggerFactory.getLogger( AppApiCriteriaEx1.class );
	
	public static void main(String[] args) {
		System.out.println("Running AppApiCriteriaEx1 ...");
		
		log.debug("Add new jdbc url property for database bank ...");		
		// if your persistence.xml file does not contain all settings already, you
		// can add vendor settings to a map 
		Properties props = new Properties ();
		props.setProperty("javax.persistence.jdbc.url", "jdbc:h2:./bank");

		log.debug("Create persistence manager");		
		// First API Criteria example: find all instances of entities bank.compte
		// JPQL: SELECT c FROM Compte c
		// APÏ Criteria ...		
		
		// Récupérer l'EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp", props);
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		// Création d'une requête se basant sur la classe Compte
		CriteriaQuery<Compte> cq = cb.createQuery(Compte.class);
		// On prédit l'utilisant de la clause FROM
		Root<Compte> c = cq.from(Compte.class);
		// Appel de la méthode SELECT c
		cq.select(c);
		// Exécution de la requête
		TypedQuery<Compte> tq = em.createQuery(cq);
		List<Compte> comptes = tq.getResultList();
		
		displayResult(comptes);
		
		log.debug("Close Entity Manager");
		em.close();
		emf.close();
	}

	
	private static void displayResult(List<Compte> comptes) {
		System.out.println(
				String.format(
						"%4s	|	%4s" ,
						"NOM",
						"SOLDE")
		);
		
		for (Compte compte : comptes) {
			System.out.println(
					String.format(
							"%4s	|	%4s" ,
							compte.getNom(),
							compte.getSolde())
			);			
		}
		
	}
	
}
