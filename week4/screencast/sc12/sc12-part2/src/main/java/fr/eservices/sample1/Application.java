package fr.eservices.sample1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
	
	// On annote ces 3 composants de Application avec l'annotation
	// Spring @Autowired. Ensuite, on défini ces 3 objets (dans leurs classes respectives)
	// en tant que composants (@Component)
	@Autowired
	Greeter greeter;
	@Autowired
	Printer printer;
	@Autowired
	Welcome welcome;
	
	public Application() {
		
/*		// First. Initialize properties:permet à l'application de s'exécuter correctement 
		greeter = new Greeter();
		printer = new Printer();
		welcome = new Welcome();
		
		// Second: l'objectif est de remplacer la construction de ces 3 objets par de l'injection
		 * de composants (@Autowired) à l'aide de Spring
		
		
*/
	}
	
	public void run() {
		String name = welcome.askName();
		String response = greeter.hello(name);
		printer.print( response );
	}
	
	public static void main(String[] args) {
		// new Application().run();
		
		/**
		 * Définition d'un contexte Spring configuré à l'aide d'annotations.
		 * On défini ce contexte en utilisant Application.class
		 */
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
		/**
		 * Par l'intermédiaire du contexte précédemment défini, on récupère un Bean de type Application
		 * Ensuite, on demande à Spring de me construire un objet Application déjà configuré, c'est-à-dire
		 * avec les dépendances déjà injectées. Pour se faire, on annote l'objet Application avec @Configuration
		 */
		Application app = ctx.getBean(Application.class);
		app.run();
		/**
		 * Si on exécute sans placé l'annotation @ComponentScan, une erreur est génerée (UnsatisfiedDependencyException et NoSuchBeanDefinitionException)
		 * car Spring ne sait pas où aller chercher ces compposants.
		 * @ComponentScan permet au contexte défini pour Spring de savoir dans quel PACKAGE Spring doit aller chercher et injecter ces composants.
		 */
	}

}
