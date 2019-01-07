package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SQLiteJDBCDriverConnection {
     /**
     * Connect to a sample database
     */
    public static void connect(String dbFilePath) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + dbFilePath;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite database " + dbFilePath + " has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
		    System.out.println("Connection to SQLite database " + dbFilePath + "  has been closed.");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		connect( args[0] );
    }
}
