package examples;

import java.io.File;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompteApp {

	private static Logger log = LoggerFactory.getLogger( Compte.class );
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	/**
	 * Transfer amount by decrementing and incrementing its value from an account to another 
	 * @param em	EntityManager
	 * @param from	Account to transfer from
	 * @param to	Account to transfer to
	 * @param amnt	Amount to transfer
	 */
	public static void transferFunds (EntityManager em, Compte from, Compte to, double amnt)
	{
	    // Note: It would be better practice to move the transaction demarcation
	    // code out of this method. The code below is for example purposes only.
	    EntityTransaction trans = em.getTransaction ();
	    trans.begin ();
	    try
	    {
	        from.decrementAccount (amnt);
	        to.incrementAccount (amnt);
	        trans.commit ();
	    }
	    catch (RuntimeException re)
	    {
	        if (trans.isActive ())
	            trans.rollback ();   // Alternative: Fix error and retry.
	    	System.out.println("Resources closing ...");
			closeResources();
	        throw re;
	    }
	}
	
	private static void closeResources() {
		em.close();
		emf.close();
	}

	public static void main(String[] args) {
		System.out.println("Running CompteApp ...");
		
		// quickest way to delete tables and data ...
		new File("./bank.mv.db").delete();
		
		log.debug("Create persistence manager");		
		// if your persistence.xml file does not contain all settings already, you
		// can add vendor settings to a map 
		Properties props = new Properties ();
		props.setProperty("javax.persistence.jdbc.url", "jdbc:h2:./bank");
		
		// create the factory defined by the "myApp" entity-manager entry on bank database
		emf = Persistence.createEntityManagerFactory ("myApp", props);	
		em = emf.createEntityManager();
		
		log.debug("Create entities");
		// Création d'un ensemble de comptes
		Compte bob = new Compte();
		bob.setNom("Bob");;
		bob.setSolde(100.0);		
		
		Compte anne = new Compte();
		anne.setNom("Anne");
		anne.setSolde(100.0);
		
		log.debug("Persist entities in a transaction");
		// Débuter une transactin JPA. Toutes les opérations d'écriture d'EntityManager se font
		// dans un contexte de transactions
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();	// Début de la transaction
		
		// Persistence des objets par JPA qui va mapper l'ensemble de ces objets dans les colonnes de la BdD 
		// accessible par le driver JDBC
		em.persist(bob);
		em.persist(anne);
						
		transaction.commit();	// Fin de la transaction
		
		log.debug("Update user entities account amounts in a transaction untill one is less or equal to zero. Then transaction will rollack");
		transferFunds(em, bob, anne, 50); 
		transferFunds(em, anne, bob, 75);
		transferFunds(em, bob, anne, 100);
		transferFunds(em, anne, bob, 20);
		// Bob account < 0. RuntimeException is throwned and transaction rollback
		// Final account values should be: Bob +45.0 and Anne +155.0
		transferFunds(em, bob, anne, 50);	// bob account < 0 (=-5)
		
		log.debug("Close Entity Manager");
		closeResources();
	}	
	
}
