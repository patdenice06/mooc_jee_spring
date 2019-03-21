package testing;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eservices.drive.dao.CatalogDao;
import fr.eservices.drive.dao.CatalogDaoJPAImpl;
import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.model.Category;

public class AppTestDao {
	private static Logger log = LoggerFactory.getLogger( AppTestDao.class );
	
	public static void main(String[] args) throws DataException {
		System.out.println("Running AppTestDao ...");
		
		log.debug("Create persistence manager");	
		Map<String, String> params = new HashMap<>();
		params.put("hibernate.hbm2ddl.auto", "create");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp", params);
		EntityManager em = emf.createEntityManager();
		
		Thread.setDefaultUncaughtExceptionHandler( new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				emf.close();
			}
		});
		
		// User adding...
		log.debug("Persist 5 Category entities");
		Category category1 = new Category("Marché", 2);
		Category category2 = new Category("Boissons", 1);
		Category category3 = new Category("Maison", 3);
		Category category4 = new Category("Papeterie & fournitures", 5);
		Category category5 = new Category("Petit électro & multimédia", 4);
		
		EntityTransaction tx = em.getTransaction();		
		tx.begin();
		try {	
			
			em.persist(category1);
			em.persist(category2);
			em.persist(category3);
			em.persist(category4);
			em.persist(category5);
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}
		
		
		log.debug("List of categories");		
		CatalogDao dao = new CatalogDaoJPAImpl(em);		
		System.out.println("List of categories");
		
		List<Category> categories;
		try {
			categories = dao.getCategories();
			if( categories.isEmpty() ) {
				System.out.println("table db.CATEGORY is empty");
			}
			else {
				for ( Category cat : categories ) {
					System.out.println( cat.getId() +" "+ cat.getName() +" "+ cat.getOrderIdx() );
				}				
			}
		} catch( DataException e ) {
			e.printStackTrace();
			throw new DataException("DataException");
		}
		
		emf.close();
	}

}
