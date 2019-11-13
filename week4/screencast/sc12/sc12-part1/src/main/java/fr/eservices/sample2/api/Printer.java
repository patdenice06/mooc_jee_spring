package fr.eservices.sample2.api;

import org.springframework.stereotype.Component;

@Component
public interface Printer {
	/**
	 * Print a message
	 * 
	 * @param message
	 */
	public void print(String message);
}
