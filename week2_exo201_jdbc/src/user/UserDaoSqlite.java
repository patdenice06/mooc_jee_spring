package user;

import java.sql.*;

public class UserDaoSqlite implements UserDao {
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new Error(e);
		}
	}
	
	protected Connection conn = null;
	private String jdbcUrl = null;
	
	public UserDaoSqlite( String userDBFilePath ) throws SQLException {
		
		try {			
		
			this.jdbcUrl = "jdbc:sqlite:" + userDBFilePath;
			this.conn = DriverManager.getConnection(jdbcUrl );	
            System.out.println("Connection to SQLite has been established.");            
			
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	@Override
	public void closeConnection() {
		try {
	        if (conn != null) {
	            conn.close();
				System.out.println("Connection to SQLite has been closed.");
	        }
	    } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	    }		
	}
	
	
	@Override
	public void add(User user, String password) {
		String sql = "INSERT INTO users(firstname,lastname,email, password) VALUES(?,?,?,?)";
		PreparedStatement pstm = null;
		
		// it refuses existing user (mail)
		if( exists( user.getEmail() ) < 0 ) {
			 // OK: mail does not exist
			try{
				
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, user.getFirstname());
				pstm.setString(2, user.getLastname());
				pstm.setString(3, user.getEmail());			
				pstm.setString(4, password);			
				int result = pstm.executeUpdate();
				
				System.out.println(result + " user added");		    		
				
			}catch(SQLException e){
				throw new RuntimeException( "Unable to add a user. " + e.getMessage() );
			}			
		}		
	}
	
	
	@Override
	public void update(User user, String password) {
		String sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, password = ? WHERE id = ?";
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getFirstname());
			pstm.setString(2, user.getLastname());
			pstm.setString(3, user.getEmail());
			pstm.setString(4, password);
			pstm.setInt( 5, new Long( user.getId() ).intValue() );
			
			int result = pstm.executeUpdate();
			System.out.println(result + " user updated");
			
		} catch (SQLException e) {
			throw new RuntimeException("Unable to update a user. " + e.getMessage());
		}
	}
	
	
	@Override
	public User find(long id) {
		// TODO : get user data by its ID and map to User object 
		throw new RuntimeException("not yet implemented");
	}
	
	@Override
	public User findByEmail(String email) {
		// get user data by its email and map to User object 
		String sql = "SELECT * FROM users WHERE email = ?";
		User user = new User();
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			
			ResultSet rs = pstm.executeQuery();			
			boolean next = rs.next();
			
			if ( !next ) {
				pstm.close();
				rs.close();
				System.out.println( "User not found by its email: " + email );
				return null;
			}			
			else {
				user.setId( rs.getLong("id") );
				user.setFirstname( rs.getString("firstname") );
				user.setLastname( rs.getString("lastname") );
				user.setEmail( rs.getString("email") );
				
				System.out.println( "One user found by its email: " + email );
				pstm.close();
				rs.close();
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Unable to find a user by its email. " + e.getMessage());
		}
		
		return user;
	}
	
	
	@Override
	public long checkPassword(String email, String password) {
		// get user id that match, return -1 if none
		String sql = "SELECT id FROM users WHERE email = ? AND password = ?";

		try {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString( 1, email );
			pstm.setString( 2, password );
			ResultSet rs = pstm.executeQuery();
			boolean next = rs.next();
			
			if ( !next ) {
				pstm.close();
				rs.close();
				System.out.println( "Unable to retrieve a user with email = " + email + " and password = " + password);
				return -1;
			}			
			else {				
				System.out.println( "Password checked. " );
				Long id = rs.getLong( "id" );
				pstm.close();
				rs.close();		
				return id;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Unable to check password. " + e.getMessage());
		}		
	}
	
	
	@Override
	public void delete(long id) {
		// delete a user that match this ID
		String sql = "DELETE FROM users WHERE id = ?";
		
		try{
			
			PreparedStatement pstm = conn.prepareStatement( sql );
			pstm.setLong( 1, id );
			int result = pstm.executeUpdate();
			System.out.println(result + " user with ID = " + id + " deleted");
			
		}catch(SQLException e){
			throw new RuntimeException( "Unable to delete a user. " + e.getMessage() );
		}
	}
	
	
	@Override
	public long exists(String email) {
		// check if user with that mail exists
	    String query = "SELECT id FROM Users WHERE email = ?;";
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    long id = -1;
	    
	    try {
	    	
	        pstm = conn.prepareStatement(query);
	        pstm.setString(1, email);
	        rs = pstm.executeQuery();
	        if (rs.next()) {
	            id = rs.getLong("id");
	        }	    			    		
	    	
	    } catch (SQLException e) {
	    	throw new RuntimeException( "Unable to find user by its email. " + e.getMessage() );
	    }
	    
	    return id;		
	}

	
	@Override
	public Connection getConnection() {
		try {
			this.conn = DriverManager.getConnection(jdbcUrl );
		} catch (SQLException e) {
			throw new RuntimeException( "Unable to get a connection. " + e.getMessage() );
		}
		return this.conn;
	}
}
