package servlets;
import javax.servlet.http.*;

import dao.DAOFactory;
import dao.PersonsDao;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/userslist"})
public class User extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String ATT_DAO_FACTORY = "daofactory";		
	private PersonsDao personsDao;	

	@Override
	public void init() throws ServletException {
		System.out.println("UserServlet.init()");
		/* Get instance of our DAO person */
		this.personsDao = ( (DAOFactory) getServletContext().getAttribute(ATT_DAO_FACTORY) ).getPersonsDao() ;
	}	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		System.out.println("UserServlet.doGet()");		
		req.setAttribute( "persons", personsDao.listAll() );
		req.getRequestDispatcher("/WEB-INF/user-list.jsp").forward(req, resp);		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	

}





