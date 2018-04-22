package dao;
import java.util.List;

import model.Persons;

public interface PersonsDao {
	
	/*
	TODO next ...
	public void update(User user);
	public void delete(String email);
	*/
	
	List<Persons> listAll();
	void create(Persons user, String password);
	Persons find(String email);
	
}