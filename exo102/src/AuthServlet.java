import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO : only handle POST request for authentication
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// TODO : get login / password from request parameters
		
		System.out.println("Enter DoPost");	// DEBUG		
		
		String login = (String) req.getParameter("login");
		String password = (String) req.getParameter("password");
		
		System.out.println("login = " + login);	// DEBUG
		System.out.println("password = " + password);	// DEBUG		
		
		if ( login == null || password == null ) throw new ServletException("no login/password");		
		boolean succeed = "admin@foo.com".equals(login) && "admin".equals(password);
		
		System.out.println("succeed = " + succeed);	// DEBUG
		
		// TODO : if auth is OK, 
		  // add something in session for next calls, 
		  // then redirect to "welcome.jsp"
		if( succeed ) {
			HttpSession session = req.getSession();
			session.setAttribute("login",login);  
			session.setAttribute("password",password);
			resp.sendRedirect("/exo102/welcome.jsp");
		}

		// TODO : if auth KO
		  // set an "errorMessage" in request attribute
		  // forward to auth.jsp with request dispatcher
		else {
			req.setAttribute("errorMessage", "Login or password incorrect");
			req.getRequestDispatcher("/auth.jsp").forward(req, resp);
		}
		
	}
	
	// TODO : allow to disconnect with a GET to /auth with any parameter "logout" value
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
	  // TODO : check for "logout" parameter
	  //   if so : disconnect and show auth.jsp
	  //   if not : Error 500
		System.out.println("Enter DoGet");	// DEBUG
		String logout = (String) req.getParameter("logout");
		
		System.out.println("logout = " + logout);	// DEBUG
		
		if( logout != null ) {
			req.setAttribute("logoutMsg", "Vous êtes maintenant déconnecté");
			this.getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);			
		}
		else {
			this.getServletContext().getRequestDispatcher("/auth.jsp").forward(req, resp);						
		}
		
	}

}