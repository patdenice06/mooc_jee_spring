package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.PersonsDao;
import forms.DeleteUserForm;
import forms.FindUserForm;
import model.Persons;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet(description = "Delete the user found by its email", urlPatterns = { "/delete-user" })
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_DAO_FACTORY = "daofactory";
	public static final String ATT_FORM	= "form";	
	public static final String VUE = "/WEB-INF/delete-user.jsp";
	private PersonsDao personsDao;	
       
    public DeleteUser() {
        super();
    }

    
	public void init() throws ServletException {
		System.out.println("DeleteUser.init()");
		/* Get instance of our DAO person */
		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(ATT_DAO_FACTORY) ).getPersonsDao() ;		
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteUser.doGet()");
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteUser.doPost()");
		/* Prepare FindUser object */		
		FindUserForm form = new FindUserForm( personsDao );
		/* Use request object to retrieve person bean */
		Persons persons = null;
		persons = form.findPerson( request );
		/* Set form in request object */
		request.setAttribute( ATT_FORM, form );

		// Check if email exists before deleting the user
		if( persons == null )
			// email does not exist
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		else {
			/* Prepare DeleteUserForm object */
			DeleteUserForm form2 = new DeleteUserForm(personsDao);
			form2.deletePerson( request );
			request.setAttribute( ATT_FORM, form2 );			
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );			
		}
		
	}

}
