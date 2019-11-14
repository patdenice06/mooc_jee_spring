package fr.eservices.sample2.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.sample2.api.Greeter;

@Component
@Qualifier("english")
public class EnglishGreeter implements Greeter {
	
	@Override
	public String hello(String name) {
		return new fr.eservices.sample1.Greeter().hello(name);
	}

}
