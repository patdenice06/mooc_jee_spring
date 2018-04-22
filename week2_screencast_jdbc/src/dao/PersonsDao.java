package dao;
import java.util.List;

import model.Persons;

/**
 *	Manage table users.persons 
 */
public interface PersonsDao {
	
	/*
	TODO next ...
	void update(User user) throws DAOException;;
	void delete(String email) throws DAOException;;
	*/
	
	List<Persons> listAll() throws DAOException;;
	void create(Persons user, String password) throws DAOException;;
	Persons find(String email) throws DAOException;;
	
}