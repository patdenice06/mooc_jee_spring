import java.util.List;

public interface UserDao {
	
	/*
	TODO next ...
	public void create(User user, String password);
	public User find(String email);
	public void update(User user);
	public void delete(String email);
	*/
	
	public List<User> listAll();
}