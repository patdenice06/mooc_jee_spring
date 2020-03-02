package testing;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.eservices.drive.dao.CatalogDao;
import fr.eservices.drive.dao.CatalogDaoJPAImpl;
import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.model.Category;

public class AppTestDao {
	
	public static void main(String[] args) {
		
//		Map<String, String> params = new HashMap<>();
//		params.put("javax.persistence.jdbc.url", "jdbc:h2:./test_db");		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp", params); 

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");		
		EntityManager em = emf.createEntityManager();
		
		Thread.setDefaultUncaughtExceptionHandler( new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				emf.close();
			}
		});
		
		CatalogDao dao = new CatalogDaoJPAImpl(em);
		
		System.out.println("List of categories");
		
		List<Category> categories;
		try {
			categories = dao.getCategories();
			for ( Category cat : categories ) {
				System.out.println( cat );
			}
		} catch( DataException e ) {
			e.printStackTrace();
		}
		
		emf.close();
	}

}