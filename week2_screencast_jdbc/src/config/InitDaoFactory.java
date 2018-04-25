package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.DAOFactory;

/**
 * Instanciate once the DAOFactory for the scope of that web app
 */
public class InitDaoFactory implements ServletContextListener{

	private static final String ATT_DAO_FACTORY = "daofactory";
	private DAOFactory daoFactory;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Get the ServletContext when that web is loading
		ServletContext servletContext = event.getServletContext();
		
		// Instanciate the DAOFactory
		this.daoFactory = DAOFactory.getInstance();
		
		// Set this attribute for the scope of that web app with read only access
		servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// Nothing to do when closing that web app
	}
	
	

}
