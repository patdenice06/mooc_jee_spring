package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.PersonsDao;
import forms.RegistrationForm;
import forms.UpdateUserForm;
import model.Persons;

/**
 * Servlet implementation class UpdateUserSelectFields
 */
@WebServlet(description = "Update user account settings with new input fields", urlPatterns = { "/update-user-select-fields" })
public class UpdateUserSelectFields extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_DAO_FACTORY = "daofactory";	
//	public static final String ATT_USER = "persons";
	public static final String ATT_FORM	= "form";	
	public static final String VUE = "/WEB-INF/update-user-select-fields.jsp";
	private PersonsDao personsDao;
	
	
    public UpdateUserSelectFields() {
        super();
    }

    
	public void init() throws ServletException {
		System.out.println("UpdateUserSelectFields.init()");
		/* Get instance of our DAO person */
		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(ATT_DAO_FACTORY) ).getPersonsDao() ;		
	}    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateUserSelectFields.doGet()");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateUserSelectFields.doPost()");
		
		// Validate new password and new confirm password if any
		String newPassword = (String) request.getParameter( "inputPassword" );
		String newConfirmPassword = (String) request.getParameter( "inputConfirmPassword" );
		
		if( !newPassword.isEmpty() ) {
			/* New password must be validated before updating*/  
			// DEBUG
			System.out.println("UpdateUserSelectFields.doPost()" + " - newPassword = " + newPassword);
			System.out.println("UpdateUserSelectFields.doPost()" + " - newConfirmPassword = " + newConfirmPassword);
			Persons person = new Persons();
			RegistrationForm form = new RegistrationForm();
			form.checkPasswords( newPassword, newConfirmPassword, person );
			if( !form.getErrors().isEmpty() ) {
				/* Error on passwords validation */
				request.setAttribute( ATT_FORM, form );			
				this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );				
			}
			else {
				/* No error on passwords validation. Prepare UpdateUser object */
				updateUser( request, response);
			}			
		}
		else {	
			/* No new password to update. Prepare UpdateUser object */
			updateUser( request, response );
		}
		
	}


	private void updateUser( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		UpdateUserForm form = new UpdateUserForm( personsDao );
		form.updatePerson( request );
		
		request.setAttribute( ATT_FORM, form );	
		request.setAttribute("result", form.getResult());
		request.setAttribute("errors", form.getErrors());
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );				
		
	}

}
