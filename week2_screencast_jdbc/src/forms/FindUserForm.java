package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DAOException;
import dao.PersonsDao;
import model.Persons;

public class FindUserForm {

	private PersonsDao personsDao = null;
	private String result = null;	
	private Map<String, String> errors = new HashMap<String, String>();
	private static final String EMAIL_FIELD = "inputEmail";

	// ctor
	public FindUserForm(PersonsDao personsDao) {
		this.personsDao = personsDao;
	}

	// getters
	public String getResult() {
		return result;
	}	
	public Map<String, String> getErrors() {
		return errors;
	}

	// methods
	
	public Persons findPerson(HttpServletRequest request) {
		System.out.println("FindUserForm.findPerson()");		
		// Get find-user form input parameter 
		String email = ( (String) request.getParameter( EMAIL_FIELD ) );
		
		// DEBUG
		System.out.println( EMAIL_FIELD +" = "+  email  );
		
		Persons person = new Persons();
		
		try {			
			
			person = personsDao.find( email );
			
		} catch (DAOException e) {
			result = "Find user failure.";
			errors.put( EMAIL_FIELD, e.getMessage() );			
		}
		
		// Find user success	
		result = "Find user success.";
		
		return person;	
	}
	
}
