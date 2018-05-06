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
	 * UPDATE a Person row
	 * @param perons
	 * @throws DAOException
	 */
	void update(Persons peron) throws DAOException;;
	
	
	/**
	 * READ all rows in users.persons
	 * @return List of Persons
	 * @throws DAOException
	 */
	List<Persons> listAll() throws DAOException;;

	/**
	 * READ a Person in users.person by its email
	 * @param email
	 * @return A Person
	 * @throws DAOException
	 */
	Persons find(String email) throws DAOException;;

	/**
	 * DELETE a Person find by its email
	 * @param email
	 * @throws DAOException
	 */
	void delete(String email) throws DAOException;;
	
	
	
	
}