package user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private final String DBFILE = "/media/patrick/DATA/edu/j2e-spring/wokspace/week2_exo201_jdbc/WebContent/WEB-INF/db/users.db";
	
	
	@Override
	public void init() throws ServletException {
		// initialize user DAO
		try {
			this.userDao = new UserDaoSqlite(DBFILE);
		} catch (SQLException e) {
			throw new RuntimeException( "Unable to initialyse the DAO. " + e.getMessage() );
		}
;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  // check for "logout" parameter
		  //   if so : disconnect and show auth.jsp
		  //   if not : Error 500
			System.out.println("Enter DoGet");	// DEBUG	
			
			String logout = (String) req.getParameter("logout");		
			System.out.println("logout = " + logout);	// DEBUG
			
	        HttpSession session = req.getSession(false);
			if( logout != null ) {
				req.setAttribute("logoutMsg", "Vous êtes maintenant déconnecté");
	            session.removeAttribute("authenticate");
				this.getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);			
			}
			else {
				// logout is null
				if ( session == null || session.getAttribute("authenticate") == null )
					this.getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);			
				else	
					throw new ServletException("no logout parameter");	
			}		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO : create user using dao and redirect to login page
		
		System.out.println("Enter DoPost");	// DEBUG		
		
		// get values from Register form
		String firstname = (String) req.getParameter("firstname");
		String lastname = (String) req.getParameter("lastname");
		String email = (String) req.getParameter("email");
		String password = (String) req.getParameter("password");
		String confirmPassword = (String) req.getParameter("confirmPassword");
		
		System.out.println("firstname = " + firstname);	// DEBUG
		System.out.println("lastname = " + lastname);	// DEBUG		
		System.out.println("email = " + email);	// DEBUG		
		System.out.println("password = " + password);	// DEBUG		
		System.out.println("password confirm = " + confirmPassword);	// DEBUG		
		
		if ((firstname == null) || firstname.isEmpty()) {
	        sendError(req, resp, "Missing firstname", "firstname");
	    } else if ((lastname == null) || lastname.isEmpty()) {
	        sendError(req, resp, "Missing lastname", "lastname");
	    } else if ((email == null) || email.isEmpty()) {
	        sendError(req, resp, "Missing email", "email");
	    } else if ((password == null) || password.isEmpty()) {
	        sendError(req, resp, "Missing password", "password");
	    } else if (userDao.exists(email) != -1) {
	        sendError(req, resp, "This email is already in use", "email");
	    } else if (!password.equals(confirmPassword)) {
	        sendError(req, resp, "Incorrect password confirmation", "confirmPassword");
	    } else {
			// Create a new user
			User user = new User();
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setEmail(email);	    	
			// Create new user using DAO
			userDao.add(user, password); 			
			// Redirect to login page
			resp.sendRedirect("/week2_exo201_jdbc/auth.jsp");
	    }
		
		
		
	}

	private void sendError(HttpServletRequest req, HttpServletResponse resp, String message, String source) throws ServletException, IOException {
	    req.setAttribute("errorMessage", message);
	    req.setAttribute("errorSource", source);
	    req.getRequestDispatcher("register.jsp").forward(req, resp);		
	}

}
