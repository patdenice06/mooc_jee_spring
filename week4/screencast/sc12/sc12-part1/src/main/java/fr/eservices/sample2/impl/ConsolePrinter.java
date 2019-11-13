package fr.eservices.sample2.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.eservices.sample2.api.Printer;

@Component
@Qualifier("console")
public class ConsolePrinter implements Printer {
	
	@Override
	public void print(String message) {
		new fr.eservices.sample1.Printer().print(message);
	}

}
