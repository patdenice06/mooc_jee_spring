package fr.eservices.sample2.api;

import org.springframework.stereotype.Component;

@Component
public interface Greeter {
	/**
	 * Say Hello to someOne
	 * 
	 * @param name
	 * @return the complete hello sentence
	 */
	public String hello( String name );
}
