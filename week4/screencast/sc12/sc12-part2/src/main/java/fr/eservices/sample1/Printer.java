package fr.eservices.sample1;

import org.springframework.stereotype.Component;

@Component
public class Printer {
	
	public Printer() {}
	
	public void print(String message) {
		System.out.println( message );
	}

}
