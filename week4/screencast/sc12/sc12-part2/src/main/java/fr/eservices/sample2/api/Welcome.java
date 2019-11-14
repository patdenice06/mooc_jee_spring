package fr.eservices.sample2.api;

import org.springframework.stereotype.Component;

@Component
public interface Welcome {
	/**
	 * Welcome a user and ask his name
	 * @return user's name
	 */
	public String askName();
}
