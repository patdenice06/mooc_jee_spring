package dao;
import java.util.List;

import model.Persons;

/**
 *	Interface for CRUD opearations on table users.persons 
 */
public interface PersonsDao {
	
	/**
	 * CREATE a new Person in database
	 * @param user
	 * @param password
	 * @throws DAOException
	 */
	void create(Persons person, String password) throws DAOException;
	
	/**
	 * CREATE a new Person in database
	 * @param person
	 * @throws DAOException
	 */
	void create(Persons person) throws DAOException;

	/**
	 * READ all rows in users.persons
	 * @return List of Persons
	 * @throws DAOException
	 */
	List<Persons> listAll() throws DAOException;

	/**
	 * READ a record in users.persons if found by its email
	 * @param email
	 * @return A person or null
	 * @throws DAOException
	 */
	Persons find(String email) throws DAOException;;		
	
	/**
	 * UPDATE Update one or more fields for the person
	 * @param persons
	 * @return 
	 * @throws DAOException
	 */
	int update(Persons person) throws DAOException;
	
	/**
	 * DELETE a Person find by its email
	 * @param email
	 * @throws DAOException
	 */
	void delete(String email) throws DAOException;
	

}