package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.PersonsDao;
import forms.FindUserForm;
import model.Persons;

/**
 * Find a user in table users.persons
 */
@WebServlet("/find-user")
public class FindUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_DAO_FACTORY = "daofactory";	
	public static final String ATT_USER = "persons";
	public static final String ATT_FORM	= "form";	
	public static final String VUE = "/WEB-INF/find-user.jsp";
	public static final String VUE2 = "/WEB-INF/user-list.jsp";
	private PersonsDao personsDao;	
	
    public FindUser() {
        super();
    }

	public void init() throws ServletException {
		System.out.println("FindUserServlet.init()");
		/* Get instance of our DAO person */
		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(ATT_DAO_FACTORY) ).getPersonsDao() ;		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FindUserServlet.doGet()");
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FindUserServlet.doPost()");
		// Get input parameters from registration form
		
		/* Prepare FindUser object */		
		FindUserForm form = new FindUserForm( personsDao );
		
		/* Use request object to retrieve person bean */
		Persons persons = null;
		
		// Create a List<Persons> to allow calling to user-list.jsp				
		persons = form.findPerson( request );
		List<Persons> personList = new ArrayList<>(); 			
		personList.add(persons);		
		
		/* Set form and bean in request object */
		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_USER, personList );
				
		// display the user if found or an error message if not found
		if( persons == null )
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		else
			this.getServletContext().getRequestDispatcher( VUE2 ).forward( request, response );
	}

}
