package web.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(path="/airport/{id}", method=RequestMethod.GET)
	public String edit(
		Model model,
		@PathVariable
		String id
	) {
		log.debug("GET airport edit {}", id);
		
		Airport airport = new Airport();
		
		// get airport from dao by id (for editing)
		airport = dao.find( Integer.parseInt(id) );
		model.addAttribute("airport", airport);
		return "airport-edit";
	}
	
	
	// this method should handle form POST request
	// inject Airport from the spring form
	@RequestMapping(path="/airport/{id}", method=RequestMethod.POST)
	public String update( @ModelAttribute Airport airport ) {
		// update airport	
		System.out.println( "airportId = "+ airport.getAirportId() );
		dao.update(airport, airport.getAirportId());

		// redirect user to airport list
		return "redirect:../airports";
	}
	
	
	/**
	 * Remove airport by its given id
	 * @param id Airport id
	 * @return Redirect to airports list view
	 */
	@RequestMapping(path="/airport/delete/{id}")
	public String delete( @PathVariable String id ) {
		// Remove airport if exists
		Airport airport = new Airport();
		int airportId = Integer.parseInt(id);
		airport = dao.find( airportId );
		if( airport != null  )
			dao.delete(airport);
		else
			System.out.println( "Airport with id "+ airportId + " does not exist." );
		
		// redirect user to airport list
		return "redirect:../../airports";
	}

}
