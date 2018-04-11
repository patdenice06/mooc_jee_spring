package user;
import java.util.List;

public class UserTest {
	
	public static void main(String[] args) {
		
		UserDao dao = new UserSQLiteImpl();
		
		List<User> users = dao.listAll();
		if ( users.isEmpty() ) {
			System.out.println("Aucun utilisateur en base");
		}
		else
		for( User user : users ) {
			System.out.println( 
				String.format(
					"%40s | %16s | %16s | %3d",
					user.getEmail(),
					user.getFirstName(),
					user.getLastName(),
					user.getAge()
				)
			);
		}
	}
}