package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Instanciate our DAO
 */
public class DAOFactory {

	private static final String PROPERTIES_FILE = "/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USER_NAME = "username";
	private static final String PROPERTY_PASSWORD = "password";
	
	private String url;
	private String username;
	private String password;
	
	public DAOFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Get connection infos for the database, load the driver and return an instance of the Factory
	 * @return An instance of DAOFactory	 
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException{
		System.out.println("DAOFactory.getInstance()");
		Properties properties = new Properties();
		String url;
		String driver;
		String userName;
		String password;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();		
		InputStream propertiesFile = classLoader.getResourceAsStream( PROPERTIES_FILE );
		
		if ( propertiesFile == null ) {
			throw new DAOConfigurationException( "Properties file: " + PROPERTIES_FILE + " not found." );
		}		
		
		try {
			
			properties.load(propertiesFile);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			userName = properties.getProperty(PROPERTY_USER_NAME);
			password = properties.getProperty(PROPERTY_PASSWORD);
			
			// DEBUG
			System.out.println("\turl = " + url);
			System.out.println("\tdriver = " + driver);
			System.out.println("\tuserName = " + userName);
			System.out.println("\tpassword = " + password);
			
			
		} catch (IOException e) {
			throw new DAOConfigurationException("Failed to load the properties file " + PROPERTIES_FILE, e);
		}
		
		try {
			
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Driver not found in the classpath.", e);
		}
		
		
		DAOFactory instance = new DAOFactory( url, userName, password );
		
		return instance;		
	}
	
	/**
	 * Provide a databse connection
	 * @return  A databse connection
	 * @throws SQLException
	 */
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection( url, username, password);		
	}
	
	
	public PersonsDao getPersonsDao() {
		return new PersonsDaoMySQLImpl( this );
		}	
	
	
}
