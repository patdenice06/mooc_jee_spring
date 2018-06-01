package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.PersonsDao;
import model.Persons;

public class FindUserForm {

	private PersonsDao personsDao = null;
	Persons person = null;
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
		// Get form input parameter 
		String email = ( (String) request.getParameter( EMAIL_FIELD ) );
		
		// DEBUG
		System.out.println("FindUserForm.findPerson()" + EMAIL_FIELD +" = "+  email);
		
		person = new Persons();
		
			if( (person = personsDao.find( email) ) != null ) {
				System.out.println("FindUserForm.findPerson()" + " Email found.");
				result = "Find user success.";
				return person;
			}
			else {
				System.out.println("FindUserForm.findPerson()" + " Email not found.");
				result = "Find user failure.";
				errors.put( EMAIL_FIELD, "Email does not exist. Thanks to choose another email." );							
			}
		
		return person;	
	}

}
