package dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import model.Persons;
import static dao.DAOUtility.*;	// to call utility methods as if they belong to this class 

/**
 * Use with MySQL JDBC Driver
 */
public class PersonsDaoMySQLImpl implements PersonsDao {
	
	private DAOFactory	daoFactory;
	private static final String SQL_SELECT_BY_EMAIL = "SELECT id, registerDate, email, firstName, lastName, birthday FROM Utilisateur WHERE email = ?";	
	
	// ctor
	public PersonsDaoMySQLImpl(DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
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
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Persons person = null;		
		
		try {
			
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, SQL_SELECT_BY_EMAIL, false, email);
			resultSet = preparedStatement.executeQuery();
			
			if( resultSet.next() ) {
				person = map(resultSet);
			}
			
		} catch (SQLException e) {
			throw new DAOException( e );
		} finally {
			quietClosure(resultSet, preparedStatement, connection);
		}
		
		return person;
	}

}
