package fr.eservices.sample2.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.sample2.api.Welcome;

@Component
@Qualifier("console")
public class ConsoleWelcome implements Welcome {

	@Override
	public String askName() {
		return new fr.eservices.sample1.Welcome().askName();
	}
	
}
