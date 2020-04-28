package web.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.AirportDao;
import flights.Airport;

@Controller
public class AirportController {
	
	private static Logger log = LoggerFactory.getLogger(AirportController.class);
	
	@Autowired
	AirportDao dao;
	
	
	@RequestMapping("/airports")
	public String list(
		Model model,
		@RequestParam(required=false, defaultValue="France")
		String country
	) {
		// use dao to get airports and countries
		List<String> countries = dao.existingCountries();
		List<Airport> airports = dao.byCountry( country );
		
		model.addAttribute( "countries", countries );
		model.addAttribute( "airports", airports );
		
		return "airport-list";
	}
	
	/**
	 * This operation handle both :
	 *  - airport creation
	 *  - airport update
	 * @param id is "new" for a creation, contains id (numeric) for editing
	 */
	@RequestMapping(path="/airport/{id}")
	public String edit(
		Model model,
		@PathVariable
		String id
	) {
		Airport airport = new Airport();
		log.debug("GET airport edit {}", id);
		
		// TODO : get airport from dao by id (for editing)
//		airport = dao.find( Integer.parseInt(id) );
		model.addAttribute("airport", airport);
		return "airport-edit";
	}
	
	// TODO : this method should handle form POST request
	// TODO : inject Airport from the spring form
	public String update(  ) {
		// log.debug("POST update airport {}", airport);
		
		// TODO : update airport
		
		// TODO : redirect user to airport list
		return "xxx";
	}

}
