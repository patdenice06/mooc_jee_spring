package fr.eservices.week402.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	// configurer Spring pour venir s'insérer comme une Servlet dans Tomcat au moment de son démarrage.
	// Le plus simple est d'étendre la méthode onStartup
	// Cette classe sera ensuite appelé par Spring.
	// Pour configurer Spring, on place une classe de configuration (fr.eservices.week402.app.AppConfig).
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register( AppConfig.class );
		container.addListener( new ContextLoaderListener(dispatcherContext) );
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", DispatcherServlet.class);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/app/*");
	}

}
