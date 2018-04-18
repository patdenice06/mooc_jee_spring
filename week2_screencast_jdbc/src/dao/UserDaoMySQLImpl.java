package dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import model.User;

/**
 * Use with MySQL JDBC Driver
 */
public class UserDaoMySQLImpl implements UserDao {
	
	private Connection conn;
	private String url = "jdbc:mysql://localhost:3306/users";
	private String dbUser = "patrick";
	private String dbPassword = "pat123";
	
	public UserDaoMySQLImpl( ) {
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

	public List<User> listAll() {
		// get all users and assigned each to a list
		String query = "select * from persons";
		List<User> res = new ArrayList<>();
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			
			while(rs.next() ) {
				User user = new User();
				
				user.setId( rs.getInt("id") );
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
	public void create(User user, String password) {
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
	public User find(String email) {		
		String query = "SELECT * FROM persons WHERE email = '" + email + "'";;
		User user = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = conn.createStatement();			
			rs = stmt.executeQuery(query);
			
			while ( rs.next() ) {
				// As 'email' column is UNIQUE, only one User is retrieved
				user = new User();
				user.setId( rs.getInt("id") );
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
