package test;
import java.util.List;

import dao.UserDao;
import dao.UserDaoMySQLImpl;
import model.User;

public class UserTest {
	
	public static void main(String[] args) {
		
		UserDao dao = new UserDaoMySQLImpl();
		
		// Create new User
		User newUser = new User();
		newUser.setEmail("clint@eastwood.com");
		String password = "clint123";
		newUser.setFirstName("Clint");
		newUser.setLastName("EASTWOOD");
//		try {
//			newUser.setBirthday( new SimpleDateFormat("yyyy-MM-dd").parse("1930-05-31") );	// Sat May 31 00:00:00 CEST 1930	
//		} catch (ParseException e) {
//			throw new Error("Failed to parse birthday. " + e);
//		}
		dao.create(newUser, password);
		
		// List all users
		List<User> users = dao.listAll();
		if ( users.isEmpty() ) {
			System.out.println("Aucun utilisateur en base");
		}
		else
		for( User user : users ) {
			System.out.println( 
				String.format(
					"%40s | %16s | %16s	| %20s	| %3d",
					user.getEmail(),
					user.getFirstName(),
					user.getLastName(),
					user.getBirthday(),
					user.getAge()
				)
			);
		}
		
	}
}