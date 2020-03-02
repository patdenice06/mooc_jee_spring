package test.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import fr.eservices.drive.dao.CatalogDao;
import fr.eservices.drive.dao.CatalogDaoJPAImpl;

/**
 * First customer will fill its first cart of articles
 * @author Patrick GIRAUD
 *
 */
public class firstCartCustomer {

	CatalogDao dao;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static EntityManagerFactory emf;
	
	@BeforeClass
	public static void initEmf() throws IOException {
		
//		ZipFile zipFile = new ZipFile(new File("./test_db.zip"));
//		InputStream is = zipFile.getInputStream(zipFile.getEntry("test_db.mv.db"));
//		FileOutputStream out = new FileOutputStream(new File("test_db.mv.db"));
//		byte[] buffer = new byte[64000];
//		int read;
//		while ( (read = is.read(buffer)) > 0 ) {
//			out.write(buffer, 0, read);
//		}
//		out.close();
//		is.close();
//		zipFile.close();
		
		Map<String, String> params = new HashMap<>();
		params.put("javax.persistence.jdbc.url", "jdbc:h2:./test_db");
		emf = Persistence.createEntityManagerFactory("myApp", params);
	}
	
	@AfterClass
	public static void closeEmf() {
		emf.close();
	}
	
	@Before
	public void setupDao() {
		dao = new CatalogDaoJPAImpl(emf.createEntityManager());
	}	
	
}
