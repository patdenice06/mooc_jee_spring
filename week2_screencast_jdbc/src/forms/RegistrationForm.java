package forms;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import dao.DAOException;
import dao.PersonsDao;
import model.Persons;

/**
 * Create a new Person in databse if its email does not yet exists  
 */
public class RegistrationForm {

	private PersonsDao personsDao = null;
	private String result = null;	
	private Map<String, String> errors = new HashMap<String, String>();
	private static final String DIGEST_ALGO = "SHA-256";		
	private static final String EMAIL_FIELD = "inputEmail";
	private static final String PASSWORD_FIELD = "inputPassword";
	private static final String CONFIRM_PASSWORD_FIELD = "inputConfirmPassword";
	private static final String FIRSTNAME_FIELD = "inputFirstname";
	private static final String LASTNAME_FIELD = "inputLastname";
	private static final String BIRTHDAY_FIELD = "inputBirthday";
	
	
	public RegistrationForm(PersonsDao personsDao) {
		this.personsDao = personsDao;
	}

	
	public RegistrationForm() {
	}



	/**
	 * Set an error entry
	 * @param field	The field of the form that caused the error
	 * @param message	The error cause
	 */
	private void setError(String field, String message) {
		errors.put(field, message);		
	}

	/**
	 * Error getter
	 * @return Errors map
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * Result getter
	 * @return	The result
	 */
	public String getResult() {
		return result;
	}

	public static String getEmailField() {
		return EMAIL_FIELD;
	}	
	
	
	
	/**
	 * Set Person bean fields from registration form
	 * @param request
	 * @return Person bean
	 * @throws FormValidationException 
	 */
	public Persons registerPerson(HttpServletRequest request) {
		System.out.println("RegsitrationForm.registerPerson()");
		
		// Get registration form input parameters 
		String email = ( (String) request.getParameter( EMAIL_FIELD ) );		
		String password = (String) request.getParameter( PASSWORD_FIELD );
		String confirmPassword = (String) request.getParameter( CONFIRM_PASSWORD_FIELD );
		String firstName = (String) request.getParameter( FIRSTNAME_FIELD );
		String lastName = (String) request.getParameter( LASTNAME_FIELD );
		LocalDate birthday = LocalDate.parse( (String) request.getParameter( BIRTHDAY_FIELD ) );

		// DEBUG
		System.out.println( EMAIL_FIELD +" = "+  email  );
		System.out.println( PASSWORD_FIELD +" = "+ password);
		System.out.println( CONFIRM_PASSWORD_FIELD +" = "+ confirmPassword);
		System.out.println( FIRSTNAME_FIELD +" = "+  firstName );
		System.out.println( LASTNAME_FIELD +"  "+  lastName );
		System.out.println( BIRTHDAY_FIELD +" = "+ birthday.toString() );
			
		Persons person = new Persons();
		
		try {
			
			checkEmail(email, person);
			checkPasswords(password, confirmPassword, person);

			if( errors.isEmpty() ) {
				// Password, confirm password and email validated and set to to person bean
				person.setFirstName(firstName);
				person.setLastName(lastName);
				person.setBirthday(birthday);				
				personsDao.create(person);
				result = "Registration success.";
			}
			else {
				result = "Registration failure.";
			}
						
		} catch (DAOException e) {
			result = "Registration failure: An error has occurred, thanks to register again.";
			e.printStackTrace();
		}	
		
		return person;
	}

	
	/**
	 * Call email validation, and init email bean property
	 * @param email
	 * @param person
	 */
	private void checkEmail(String email, Persons person) {
		try {
			validateEmail(email);
		} catch (FormValidationException e) {
			setError(EMAIL_FIELD, e.getMessage());
			System.out.println( getErrors().get(EMAIL_FIELD) );
		}
		person.setEmail(email);
	}

	
	/**
	 * Validate email if it is unique
	 * @param email
	 * @throws FormValidationException
	 */
	private void validateEmail(String email) throws FormValidationException{
		if( personsDao.find(email) != null ) { 
			System.out.println("email already exists ==> set an error");
			throw new FormValidationException("Email already exists. Thanks to choose another email.");
		}
	}

	
	/**
	 * Call validate passwords method, encrypt passwords and set bean property "cryptedPassword"
	 * @param password
	 * @param confirmPassword
	 * @param person 
	 * @throws FormValidationException
	 */
	public void checkPasswords(String password, String confirmPassword, Persons person) {
			try {
				validatePasswords(password, confirmPassword);
			} catch (FormValidationException e) {
				if( !e.getMessage().contains("Confirm") ) {
					setError(PASSWORD_FIELD, e.getMessage());
					setError(CONFIRM_PASSWORD_FIELD, null);					
				}
				else {
					setError(PASSWORD_FIELD, null);
					setError(CONFIRM_PASSWORD_FIELD, e.getMessage());										
				}					
			}
			
			// Passwords are validated, so encrypt password
			ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
			passwordEncryptor.setAlgorithm( DIGEST_ALGO );
			passwordEncryptor.setPlainDigest( false );
			String cryptedPassword = passwordEncryptor.encryptPassword( password);
			
//			passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
			
			person.setCryptedPassword( cryptedPassword );
	}
	
	
	/**
	 * Validate password and confirm password
	 * @param password
	 * @param confirmPassword
	 * @throws FormValidationException 
	 */
	public void validatePasswords(String password, String confirmPassword) throws FormValidationException{
		if( !password.equals(confirmPassword) ) {
			throw new FormValidationException("Confirm password does not match password. Please retry. ");
		}
		else if ( password.trim().length() < 6 ) {
			throw new FormValidationException("Password must contains at least 6 characters long. Pease retry.");
		}
	}

	
	
}
