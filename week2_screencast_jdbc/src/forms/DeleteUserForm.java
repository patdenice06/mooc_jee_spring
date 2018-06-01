package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.PersonsDao;
import model.Persons;

public class DeleteUserForm {

	private PersonsDao personsDao = null;
	Persons person = null;
	private String result = null;	
	private Map<String, String> errors = new HashMap<String, String>();
	private static final String EMAIL_FIELD = "inputEmail";
	
	// ctor
	public DeleteUserForm(PersonsDao personsDao) {
		this.personsDao = personsDao;
	}
	
	// getters
	public String getResult() {
		return result;
	}	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void deletePerson(HttpServletRequest request) {
		// Get form input parameter 
		String email = ( (String) request.getParameter( EMAIL_FIELD ) );
		
		// DEBUG
		System.out.println("DeleteUserForm.deletePerson()." + EMAIL_FIELD +" = "+  email);

		personsDao.delete(email);			
		
		// email found
		errors.clear();
		result = "Deleting user success.";
	}
	
	
	
	
}
