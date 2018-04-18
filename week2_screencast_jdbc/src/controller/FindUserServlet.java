package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoMySQLImpl;

/**
 * Find a user in table users.persons
 */
@WebServlet("/find-user")
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao dao;   
	
    public FindUserServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("FindUserServlet.init()");
		dao = new UserDaoMySQLImpl();		
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
