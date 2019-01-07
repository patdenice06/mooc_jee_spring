package utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for DAO 
 */
public final class DAOUtility {

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
				System.out.println( "Failed to close ResultSet: " + e.getMessage() );
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
			System.out.println( "Failed to close Statement: " + e.getMessage() );
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
			System.out.println( "Failed to close connection : " + e.getMessage() );
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
	
}
