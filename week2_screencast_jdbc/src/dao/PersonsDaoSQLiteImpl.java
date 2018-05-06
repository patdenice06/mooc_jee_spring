package dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import model.Persons;

/**
 * Use with SQLite JDBC Driver
 */

public class PersonsDaoSQLiteImpl implements PersonsDao {
	
	private Connection conn;
	private String url = "jdbc:mysql://localhost:3306/users";
	private String dbUser = "patrick";
	private String dbPassword = "pat123";
	
	public PersonsDaoSQLiteImpl( ) {
		/* Load mysql jdbc driver */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Error("Class not found for MySQL JDBC driver. " + e);
		}
		
		// Open a connection with a proper jdbc url				
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPassword);
		} catch (SQLException e) {
			throw new Error("Failed to connect to the database. " + e);
		}
	}

	public List<Persons> listAll() {
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
	public void create(Persons user, String password) {

	}

	@Override
	public Persons find(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Persons peron) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String email) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
