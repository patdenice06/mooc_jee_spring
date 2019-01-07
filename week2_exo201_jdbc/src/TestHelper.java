import java.sql.Connection;
import java.sql.SQLException;

import user.UserDao;
import user.UserDaoSqlite;

public class TestHelper {
	
	private static Connection connection;
	
	private static class TestUserDao extends UserDaoSqlite {
		TestUserDao(String str) throws SQLException {
			super(str);
			TestHelper.connection = this.conn;
		}
	}
	
	
	private static TestUserDao dao = null;
	
	public static UserDao createUserDao() throws SQLException {
		if ( dao != null ) return dao;
		
		dao = new TestUserDao( "/media/patrick/DATA/edu/j2e-spring/wokspace/week2_exo201_jdbc/WebContent/WEB-INF/db/users.db" );
		return dao;
	}
	
	public static int updateDb(String sql) throws SQLException {
		
		if ( connection == null ) createUserDao();
		
		return connection
			.createStatement()
			.executeUpdate(sql);
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	// Method added
	public static void closeConnection() {
		try {
	        if (connection != null) {
	            connection.close();
				System.out.println("Connection to SQLite has been closed.");
	        }
	    } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	    }			
	}
	
}
