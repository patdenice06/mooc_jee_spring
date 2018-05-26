package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonsDao;
import forms.RegsitrationForm;
import model.Persons;
import dao.DAOFactory;

/**
 * User registration servlet
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String ATT_DAO_FACTORY = "daofactory";	
	public static final String ATT_USER = "user";
	public static final String ATT_FORM	= "form";
	public static final String VUE = "/WEB-INF/registration.jsp";
	private PersonsDao personsDao;	
       
    public Registration() {
        super();
    }

	@Override
	public void init() throws ServletException {
		System.out.println("UserServlet.init()");
		/* Get instance of our DAO person */
		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(ATT_DAO_FACTORY) ).getPersonsDao() ;
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegistrationServlet.doGet()");
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		// Get input parameters from registration form
		
		/* Prepare registration object */
		RegsitrationForm form = new RegsitrationForm( personsDao );
		
		/* Use request object to retrieve person bean */
		Persons person = null;
		person = form.registerPerson( request );
		
		/* Set form and bean in request object */
		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_USER, person );
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );		
		
		// show new persons list
//		request.setAttribute( "persons", personsDao.listAll() );
//		request.getRequestDispatcher("/WEB-INF/user-list.jsp").forward(request, response);		

		
	}

}
