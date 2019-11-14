package fr.eservices.sample2.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.sample2.api.Greeter;

@Component
@Qualifier("french")
public class FrenchGreeter implements Greeter {
	
	@Override
	public String hello(String name) {
		if ( name == null || name.isEmpty() ) name = " tout le monde !";
		return "Bonjour " + name;
	}

}
