package servlets;
import javax.servlet.http.*;

import dao.PersonsDao;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/userslist"})
public class User extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	PersonsDao dao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		System.out.println("UserServlet.doGet()");		
		req.setAttribute( "persons", dao.listAll() );
		req.getRequestDispatcher("/WEB-INF/user-list.jsp").forward(req, resp);		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	

}





