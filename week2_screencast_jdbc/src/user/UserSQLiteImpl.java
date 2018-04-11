package user;
import java.sql.*;
import java.util.*;

/**
 * Use with SQLite JDBC Driver
 */

public class UserSQLiteImpl implements UserDao {
	
	private Connection conn;
	private String url = "jdbc:mysql://localhost:3306/users";
	private String dbUser = "patrick";
	private String dbPassword = "pat123";
	
	public UserSQLiteImpl( ) {
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
				user.setBirthday( rs.getDate("birthday") );
				
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

}
