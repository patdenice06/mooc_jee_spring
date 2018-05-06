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
	
//	public static final String CONF_DAO_FACTORY = "daofactory";	// not config.initDaoFactory.ATT_DAO_FACTORY ???
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
//		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY) ).getPersonsDao() ;
		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(ATT_DAO_FACTORY) ).getPersonsDao() ;
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegistrationServlet.doGet()");
		/* Dispaly registration page */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		// récupérer les paramètres saisis dans le formulaire de registration
		
		/* Prépare registration object */
		RegsitrationForm form = new RegsitrationForm( personsDao );
		
		/* Use request object to retrieve person bean */
		Persons person = form.registerPerson( request );
		
		/* Set form and bean in request object */
		request.setAttribute( ATT_FORM, form );
		request.setAttribute( ATT_USER, person );		
		
		
//		String email = (String) request.getParameter("inputEmail");
//		String password = (String) request.getParameter("inputPassword");
//		String firstname = (String) request.getParameter("inputFirstname");
//		String lastname = (String) request.getParameter("inputLastname");
//		String  birthday = (String) request.getParameter("inputBirthday");
		
		// show new users list
		request.setAttribute( "persons", personsDao.listAll() );
		request.getRequestDispatcher("/WEB-INF/user-list.jsp").forward(request, response);		

		
	}

}
