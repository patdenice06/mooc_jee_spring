package forms;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import dao.PersonsDao;
import model.Persons;

/**
 * Create a new Person in databse if its email does not yet exists  
 */
public class RegsitrationForm {

	private PersonsDao personsDao;
	
	public RegsitrationForm(PersonsDao personsDao) {
		this.personsDao = personsDao;
	}

	public Persons registerPerson(HttpServletRequest request) {
		System.out.println("RegsitrationForm.registerPerson()");
		String password = (String) request.getParameter("inputPassword");
		
		Persons person = new Persons();
		person.setEmail( (String) request.getParameter("inputEmail") );
		person.setFirstName( (String) request.getParameter("inputFirstname") );
		person.setLastName( (String) request.getParameter("inputLastname") );
		person.setBirthday( LocalDate.parse( (String) request.getParameter("inputBirthday") ) );
		
		// DEBUG
		System.out.println("email : " + person.getEmail());
		System.out.println("password : " + password);
		System.out.println("firstname : " + person.getFirstName());
		System.out.println("lastname : " + person.getLastName());
		System.out.println("birthday : " + person.getBirthday().toString());
		
		// TODO If email already exists then set an error, else create the new person
		if( personsDao.find( person.getEmail() ) != null ) {
			
		}
		else {
			personsDao.create(person, password);			
		}
		
		return person;
	}

	
	
}
