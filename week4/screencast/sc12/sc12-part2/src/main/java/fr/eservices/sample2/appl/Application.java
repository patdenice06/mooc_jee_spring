package fr.eservices.sample2.appl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.eservices.sample2.api.Greeter;
import fr.eservices.sample2.api.Printer;
import fr.eservices.sample2.api.Welcome;

public class Application {
	
	Welcome welcome;
	Greeter greeter;
	Printer printer;
	

	public Application() {
	}
	
	
	public void setWelcome(Welcome welcome) {
		this.welcome = welcome;
	}

	public void setGreeter(Greeter greeter) {
		this.greeter = greeter;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	
	public void run() {
		String name = welcome.askName();
		String response = greeter.hello(name);
		printer.print( response );
	}
	
	
	public static void main(String[] args) {
		// Create an xml spring context
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		// Get Application From context
		Application app = ctx.getBean(Application.class);
		// Call run
		app.run();
	}
}
