package forms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.PersonsDao;
import model.Persons;

public class UpdateUserForm {

	private PersonsDao personsDao = null;
	Persons person = null;
	private String result = null;	
	private Map<String, String> errors = new HashMap<String, String>();
	private static final String ID_FIELD = "inputID";
	private static final String EMAIL_FIELD = "inputEmail";
	private static final String PASSWORD_FIELD = "inputPassword";
	private static final String FIRSTNAME_FIELD = "inputFirstname";
	private static final String LASTNAME_FIELD = "inputLastname";
	private static final String BIRTHDAY_FIELD = "inputBirthday";
	
	// ctor
	public UpdateUserForm(PersonsDao personsDao) {
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
	public void updatePerson(HttpServletRequest request) {
		// Get form input parameter 
		String id = ( (String) request.getParameter( ID_FIELD ) );
		String email = ( (String) request.getParameter( EMAIL_FIELD ) );
		String password = ( (String) request.getParameter( PASSWORD_FIELD ) );
		String firstname = ( (String) request.getParameter( FIRSTNAME_FIELD ) );
		String lastname = ( (String) request.getParameter( LASTNAME_FIELD ) );
		String birthday = (String) request.getParameter( BIRTHDAY_FIELD );
		LocalDate birthdayLD = null;
		// If birthday is null it could not be parsed to a LocalDate object
		if( !birthday.isEmpty() )
			birthdayLD = LocalDate.parse( birthday );
		
		// DEBUG
		System.out.println( "UpdateUserForm.updatePerson()" + "*** New user account settings before updating ***");
		System.out.println( "UpdateUserForm.updatePerson()" + " - " + ID_FIELD +" = "+  id );
		System.out.println( "UpdateUserForm.updatePerson()" + " - " + EMAIL_FIELD +" = "+  email );
		System.out.println( "UpdateUserForm.updatePerson()" + " - " + PASSWORD_FIELD +" = "+  password );
		System.out.println( "UpdateUserForm.updatePerson()" + " - " + FIRSTNAME_FIELD +" = "+  firstname );
		System.out.println( "UpdateUserForm.updatePerson()" + " - " + LASTNAME_FIELD +" = "+  lastname );
		System.out.println( "UpdateUserForm.updatePerson()" + " - " + BIRTHDAY_FIELD +" = "+  birthday.toString() );

		// Set the new user bean properties for updating by id
		person = new Persons();
		
		person.setEmail( email );
		
		// Column 'password' cannot be null in table persons
		if( !password.isEmpty() )
			// Encrypt and set the new input password
			person.encryptPassword( password );
		
		person.setFirstName( firstname );
		person.setLastName( lastname );
		
//		if( birthday != null )
		person.setBirthday( birthdayLD );
		
		// ID must be cast to Long and is never null
		person.setId( new Long( id ) );
		
		// Call update DAO method
		personsDao.update( person );
	}
	
	
}
