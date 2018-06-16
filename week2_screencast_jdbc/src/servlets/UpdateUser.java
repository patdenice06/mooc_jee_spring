package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.PersonsDao;
import forms.FindUserForm;
import model.Persons;

@WebServlet(description = "Update a user selected by its email", urlPatterns = { "/update-user" })
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_DAO_FACTORY = "daofactory";	
	public static final String ATT_USER = "person";
	public static final String ATT_FORM	= "form";	
	public static final String VUE = "/WEB-INF/update-user-check-email.jsp";
	public static final String VUE2 = "/WEB-INF/update-user-select-fields.jsp";
	private PersonsDao personsDao;	
       
    public UpdateUser() {
        super();
    }

	public void init() throws ServletException {
		System.out.println("UpdateUser.init()");
		/* Get instance of our DAO person */
		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(ATT_DAO_FACTORY) ).getPersonsDao() ;		
	}

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("UpdateUser.doGet()");
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );		
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateUser.doPost()");
		/* Prepare FindUser object */		
		FindUserForm form = new FindUserForm( personsDao );
		/* Use request object to retrieve person bean */
		Persons person = null;
		person = form.findPerson( request );
		
		/* Set form in request object */
		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_USER, person );

		// Check if email exists before updating the user
		if( person == null ) {
			// email does not exist
			System.out.println("UpdateUser.doPost()" + " - Email does not exist. Nothing to update.");
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );			
		}
		else {
			// email exist. User field(s) can be updated			
			// Display JSP page that presents the user fields to update
			
			// DEBUG
			System.out.println( "UpdateUser.doPost()" + " - " + person.getId() );			
			System.out.println( "UpdateUser.doPost()" + " - " + person.getEmail() );			
			System.out.println( "UpdateUser.doPost()" + " - " + person.getCryptedPassword() );			
			System.out.println( "UpdateUser.doPost()" + " - " + person.getFirstName() );			
			System.out.println( "UpdateUser.doPost()" + " - " + person.getLastName() );			
			System.out.println( "UpdateUser.doPost()" + " - " + person.getBirthday() );
			
			this.getServletContext().getRequestDispatcher( VUE2 ).forward( request, response );			
		}
				
	}

}
