package controller;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoMySQLImpl;
import model.User;

/**
 * User registration servlet
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao;
       
    public RegistrationServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
		System.out.println("UserServlet.init()");
		dao = new UserDaoMySQLImpl();
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegistrationServlet.doGet()");
		request.getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupérer les paramètres saisis dans le formulaire de registration
		String email = (String) request.getParameter("inputEmail");
		String password = (String) request.getParameter("inputPassword");
		String firstname = (String) request.getParameter("inputFirstname");
		String lastname = (String) request.getParameter("inputLastname");
		String  birthday = (String) request.getParameter("inputBirthday");
		
		System.out.println("email : " + email);
		System.out.println("password : " + password);
		System.out.println("firstname : " + firstname);
		System.out.println("lastname : " + lastname);
		System.out.println("birthday : " + birthday);
		
		// create this new User
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setBirthday( LocalDate.parse(birthday) );
		
		// record this User in table users.persons
		dao.create(user, password);
		
		// show new users list
		request.setAttribute( "persons", dao.listAll() );
		request.getRequestDispatcher("/WEB-INF/user-list.jsp").forward(request, response);		

		
	}

}
