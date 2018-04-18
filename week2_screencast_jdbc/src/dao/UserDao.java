package dao;
import java.util.List;

import model.User;

public interface UserDao {
	
	/*
	TODO next ...
	public void update(User user);
	public void delete(String email);
	*/
	
	public List<User> listAll();
	public void create(User user, String password);
	public User find(String email);
	
}