package fr.eservices.sample2.appl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.eservices.sample2.api.Greeter;
import fr.eservices.sample2.api.Printer;
import fr.eservices.sample2.api.Welcome;

@Configuration
@ComponentScan("fr.eservices.sample2.impl")
public class Application {
	
	@Autowired
	@Qualifier("swing")
	Welcome welcome;
	@Autowired
	@Qualifier("french")
	Greeter greeter;
	@Autowired
	@Qualifier("console")
	Printer printer;
	

	public Application() {
	}
	
	public void run() {
		String name = welcome.askName();
		String response = greeter.hello(name);
		printer.print( response );
	}
	
	public static void main(String[] args) {
		// Create a spring context
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
		// Get Application From context
		Application app = ctx.getBean(Application.class);
		// Call run
		app.run();
	}
}
