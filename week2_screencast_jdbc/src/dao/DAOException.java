package dao;

public class DAOException extends RuntimeException{
	/*
	 * ctors
	 */

	private static final long serialVersionUID = 1L;

	public DAOException( String message ) {
		super( message );
	}
	
	public DAOException( String message, Throwable cause ) {
		super( message, cause );
	}
	
	public DAOException( Throwable cause ) {
		super( cause );
	}
}
