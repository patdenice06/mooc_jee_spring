package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.PersonsDao;
import forms.UpdateUserForm;

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
//	public static final String VUE2 = "/WEB-INF/update-user-select-fields.jsp";
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
		
		// DEBUG
		System.out.println( "*** ID and new user account settings ***" );
		System.out.println( "UpdateUserSelectFields.doPost()" + " - id = " + request.getParameter("inputID") );
		System.out.println( "UpdateUserSelectFields.doPost()" + " - inputEmail = " + request.getParameter("inputEmail") );
		System.out.println( "UpdateUserSelectFields.doPost()" + " - inputPassword = " + request.getParameter("inputPassword") );
		System.out.println( "UpdateUserSelectFields.doPost()" + " - inputConfirmPassword = " + request.getParameter("inputConfirmPassword") );
		System.out.println( "UpdateUserSelectFields.doPost()" + " - inputFirstname = " + request.getParameter("inputFirstname") );
		System.out.println( "UpdateUserSelectFields.doPost()" + " - inputLastname = " + request.getParameter("inputLastname") );
		System.out.println( "UpdateUserSelectFields.doPost()" + " - inputBirthday = " + request.getParameter("inputBirthday") );
		
		// TODO Validate new password and new confirm password if any
		String newPassword = (String) request.getParameter( "inputPassword" );
		String newConfirmPassword = (String) request.getParameter( "inputConfirmPassword" );
		// DEBUG
		System.out.println("UpdateUserSelectFields.doPost()" + " - newPassword = " + newPassword);
		System.out.println("UpdateUserSelectFields.doPost()" + " - newConfirmPassword = " + newConfirmPassword);
		
		
		/* Prepare UpdateUser object */
		UpdateUserForm form = new UpdateUserForm( personsDao );
		form.updatePerson( request );
		
		request.setAttribute( ATT_FORM, form );			
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );				
	}

}
