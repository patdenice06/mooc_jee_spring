package fr.eservices.week402.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// Set this class as a configuration class.
@Configuration

// scan fr.eservices.week402.ctrl for components
// Permet à cette classe de chercher les composants Spring dans un package particulier
// ainsi que dans ses sous-packages.
@ComponentScan("fr.eservices.week402.ctrl")

// enable spring web mvc
@EnableWebMvc
public class AppConfig {

	// Add a method to provide an InternalReourceViewResolver
	// put views in /WEB-INF/views
	// all views should be some jsp	
	   @Bean
	   public InternalResourceViewResolver resolver() {
	      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	      resolver.setPrefix("/WEB-INF/views/");
	      resolver.setSuffix(".jsp");
	      return resolver;
	   }
}
