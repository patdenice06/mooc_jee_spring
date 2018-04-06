import java.sql.*;
import java.util.*;

public class UserSQLiteImpl implements UserDao {
	
	private Connection conn;
	
	public UserSQLiteImpl( String dbfile ) {
		
	}

	public List<User> listAll() {

			return new ArrayList<>();

	}

}
