package test;
import java.io.InputStream;
import java.util.List;

import dao.DAOConfigurationException;
import dao.DAOFactory;
import dao.PersonsDao;
import model.Persons;

public class PersonsTest {
		
	public static void main(String[] args) {
		final String PROPERTIES_FILE = "WEB-INF/dao.properties";

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propertiesFile = classLoader.getResourceAsStream( PROPERTIES_FILE );
				
		if ( propertiesFile == null ) {
			throw new DAOConfigurationException( "Properties file: " + PROPERTIES_FILE + " not found." );
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		PersonsDao personsDao = daoFactory.getPersonsDao();
		
		
		// Create new User
//		Persons newUser = new Persons();
//		newUser.setEmail("east@eastwood.com");
//		String password = "clint123";
//		newUser.setFirstName("Clint");
//		newUser.setLastName("EASTWOOD");
////		try {
////			newUser.setBirthday( new SimpleDateFormat("yyyy-MM-dd").parse("1930-05-31") );	// Sat May 31 00:00:00 CEST 1930	
////		} catch (ParseException e) {
////			throw new Error("Failed to parse birthday. " + e);
////		}
//		personsDao.create(newUser, password);
		
		// List all users
		List<Persons> personsList = personsDao.listAll();
		if ( personsList.isEmpty() ) {
			System.out.println("Aucun utilisateur en base");
		}
		else
		for( Persons person : personsList ) {
			System.out.println( 
				String.format(
					"%40s | %16s | %16s	| %20s	| %3d",
					person.getEmail(),
					person.getFirstName(),
					person.getLastName(),
					person.getBirthday(),
					person.getAge()
				)
			);
		}
		
	}
}