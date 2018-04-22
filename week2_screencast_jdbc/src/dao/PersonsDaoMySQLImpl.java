package dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import model.Persons;

/**
 * Use with MySQL JDBC Driver
 */
public class PersonsDaoMySQLImpl implements PersonsDao {
	
	private DAOFactory daoFactory;
	
	private Connection conn;
	private String url = "jdbc:mysql://localhost:3306/users";
	private String dbUser = "patrick";
	private String dbPassword = "pat123";
	
	// ctor
	public PersonsDaoMySQLImpl(DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
		
		
		
//		/* Load mysql jdbc driver */
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			throw new Error("Class not found for MySQL JDBC driver. " + e);
//		}
//		
//		// Open a connection with a proper jdbc url				
//		try {
//			conn = DriverManager.getConnection(url, dbUser, dbPassword);
//		} catch (SQLException e) {
//			throw new Error("Failed to connect to the database. " + e);
//		}
	}
	
	
	/**
	 * Utility method to initiate the PreparedStatement from database connexion, sql query and given objects args
	 * @param connection Databse connection
	 * @param sql	SQL query
	 * @param returnGeneratedKeys Returns or not a GENERATED_KEYS
	 * @param objets	SQL query parameters of differents types and sizes
	 * @return	a PreparedStatement
	 * @throws SQLException
	 */
	public static PreparedStatement initPreparedStatement( 
			Connection connection,
			String sql,
			boolean returnGeneratedKeys,
			Object... objets) throws SQLException {
		 
		PreparedStatement preparedStatement =
				connection.prepareStatement( sql, returnGeneratedKeys ?
				Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
		
		for ( int i = 0; i < objets.length; i++ ) {
			preparedStatement.setObject( i + 1, objets[i] );
		}
				
		return preparedStatement;
	}
	
	
	/**
	 * Utility method to map a row in users.persons and a Persons bean
	 * @param rs A ResultSet cursor
	 * @return	A Persons bean
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private static Persons map( ResultSet rs ) throws SQLException {
		Persons person = new Persons();
		person.setId( rs.getLong( "id" ) );
		person.setRegisterDate( rs.getTimestamp( "registerDate" ) );
		person.setEmail( rs.getString( "email" ) );
		person.setFirstName( rs.getString( "firstName" ) );
		person.setLastName( rs.getString( "lastName" ) );
		person.setBirthday( LocalDate.parse( rs.getString("birthday") ) );
		
		return person;
	}	
	
	
	/*************************************************************/
	/* Utility methods to properly closed any database resources */
	/*************************************************************/
	
	/**
	 * Quiet closure of the resultset
	 * @param resultSet
	 */
	public static void quietClosure( ResultSet resultSet ) {
		if ( resultSet != null ) {
			try { 
				
			resultSet.close();
			
			} catch ( SQLException e ) {
				System.out.println( "Échec de la fermeture du ResultSet: " + e.getMessage() );
			}
		}
	}
	
	/**
	 * Quiet closure of the statement
	 * @param statement
	 */
	public static void quietClosure( Statement statement ) {
		if ( statement != null ) {
			try {
				
			statement.close();
			
			} catch ( SQLException e ) {
			System.out.println( "Échec de la fermeture du Statement: " + e.getMessage() );
			}
		}
	}
	
	
	/**
	 * Quiet closure of the connection
	 * @param connexion
	 */
	public static void quietClosure( Connection connexion ) {
		if ( connexion != null ) {
			try {
				
			connexion.close();
			
			} catch ( SQLException e ) {
			System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
			}
		}
	}
	
	
	/**
	 * Quiet closure of the statement and the connexion  
	 * @param statement
	 * @param connexion
	 */
	public static void quietClosure( Statement statement, Connection connexion ) {
		quietClosure( statement );
		quietClosure( connexion );
	}
	
	
	/* Fermetures silencieuses du resultset, du statement et de la
	connexion */
	/**
	 * Quiet closure of the resultSet, the statement and the connection
	 * @param resultSet
	 * @param statement
	 * @param connexion
	 */
	public static void quietClosure( ResultSet resultSet, Statement statement, Connection connexion ) {
		quietClosure( resultSet );
		quietClosure( statement );
		quietClosure( connexion );
	}	
	
	
	
	/*********************************/
	/* Interface implemented methods */
	/*********************************/
	
	public List<Persons> listAll() throws DAOException {
		// get all users and assigned each to a list
		String query = "select * from persons";
		List<Persons> res = new ArrayList<>();
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			
			while(rs.next() ) {
				Persons user = new Persons();
				
				user.setId( (long) rs.getInt("id") );
				user.setEmail( rs.getString("email") );
				user.setFirstName( rs.getString("firstname") );
				user.setLastName( rs.getString("lastname") );
				user.setBirthday( LocalDate.parse( rs.getString("birthday") ) );
				
				res.add(user);
			}			
		} catch (Exception e) {
			
			try {
				stm.close();
				rs.close();
				conn.close();
			} catch (SQLException e1) {
				throw new Error("Failed on closing connection resources." + e);
			}
			
			throw new Error("Unable to execute query '" + query + "' : " + e);
		}
		
		return res;

	}

	@Override
	/**
	 * Create a new User
	 */
	public void create(Persons user, String password) throws DAOException {
		PreparedStatement pstmt = null;
		String query = null;
		int nb = 0;
		try {			
			StringBuffer sb = new StringBuffer();		
			sb.append("INSERT INTO persons");
			sb.append("(email, password, firstname, lastname, birthday) ");
			sb.append("VALUES ( ");
			sb.append("?, ?, ?, ?, ?");
			sb.append(")");
			
			query = sb.toString();

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, password);
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setString(5, user.getBirthday().toString());

			nb = pstmt.executeUpdate();	// number of inserts
			
			} catch (SQLException e) {
			
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				throw new Error("Failed on closing connection resources. '" + e1);
			}
			
			throw new Error("Unable to execute query '" + query + "' : " + e);
		}
			
		System.out.println(nb + " insert(s)");		
	}

	@Override
	public Persons find(String email) throws DAOException {		
		String query = "SELECT * FROM persons WHERE email = '" + email + "'";;
		Persons user = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.createStatement();			
			rs = stmt.executeQuery(query);
			
			while ( rs.next() ) {
				// As 'email' column is UNIQUE, only one User is retrieved
				user = new Persons();
				user.setId( (long) rs.getInt("id") );
				user.setEmail( rs.getString("email") );
				user.setFirstName( rs.getString("firstname") );
				user.setLastName( rs.getString("lastname") );
				user.setBirthday( LocalDate.parse( rs.getString("birthday") ) );
			}
			
		} catch (Exception e) {
			
			try {
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e1) {
				throw new Error("Failed on closing connection resources." + e);
			}			
			
			throw new Error("Unable to execute query '" + query + "' : " + e);
		}
		
		return user;
	}

}
