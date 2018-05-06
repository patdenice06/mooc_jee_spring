package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.PersonsDao;
import dao.PersonsDaoMySQLImpl;

/**
 * Find a user in table users.persons
 */
@WebServlet("/find-user")
public class FindUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAOFactory daoFactory = null;
    private PersonsDao personsDao = null;
	
    public FindUser() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("FindUserServlet.init()");
		this.daoFactory = DAOFactory.getInstance();
		this.personsDao = new PersonsDaoMySQLImpl(daoFactory);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FindUserServlet.doGet()");
		request.getRequestDispatcher("/WEB-INF/find-user.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// show the user if it was found
		System.out.println("FindUserServlet.doPost()");
//		request.setAttribute( "persons", dao.listAll() );
		request.getRequestDispatcher("/WEB-INF/user-list.jsp").forward(request, response);		
	}

}
