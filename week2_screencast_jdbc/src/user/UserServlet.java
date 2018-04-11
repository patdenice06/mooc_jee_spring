package user;
import javax.servlet.http.*;
import javax.servlet.ServletException;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/signin"})
public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException
	{
		try {
			
			res.getWriter().println("Hello !");
			
		} catch( IOException ioe ) {
			throw new ServletException( ioe );
		}

	}

}





