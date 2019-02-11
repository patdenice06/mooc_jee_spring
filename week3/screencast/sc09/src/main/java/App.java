import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import library.Author;
import library.Book;

public class App {
	private static Logger log = LoggerFactory.getLogger( App.class );
	
	public static void main(String argv[]) {
		System.out.println("Running App ...");
		
		log.debug("Create persistence manager");		
		// Création d'un contexte de persistence avec en paramètre le nom de la persistence unit configurée
		// dans le fichier persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		// Création d'un EntityManager permettant de persister les différents objets JPA
		EntityManager em = emf.createEntityManager();
		
		log.debug("Create entities");
		// Création d'un ensemble d'auteurs et de livres (objets métiers)
		Author lionel = new Author();
		lionel.setFirstName("Lionel");
		lionel.setLastName("Seinturier");
		
		Author renaud = new Author();
		renaud.setFirstName("Renaud");
		renaud.setLastName("Pawlak");
		
		Author jeanPhilippe = new Author();
		jeanPhilippe.setFirstName("Jean-Philippe");
		jeanPhilippe.setLastName("Retaillé");
		
		// Création d'un livre
		Book book = new Book();
		book.setIsbn("978-2-212-11408-9");
		book.setTitle("Programmation orientée aspect pour Java /J2EE");
		book.setPrice(45.0);
		
		// Associer la liste des 3 auteurs de ce livre
		book.getAuthors().add(lionel);
		book.getAuthors().add(renaud);
		book.getAuthors().add(jeanPhilippe);
		
		
		log.debug("Persist entities in a transaction");
		// Débuter une transactin JPA. Toutes les opérations d'écriture d'EntityManager se font
		// dans un contexte de transactions
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();	// Début de la transaction
		
		// Persistence des objets par JPA qui va mapper l'ensemble de ces objets dans les colonnes de la BdD 
		// accessible par le driver JDBC
		em.persist(lionel);
		em.persist(renaud);
		em.persist(jeanPhilippe);
		
		em.persist(book);
		
		transaction.commit();	// Fin de la transaction
		
		log.debug("Close Entity Manager");
		em.close();
		emf.close();
		

	}
}