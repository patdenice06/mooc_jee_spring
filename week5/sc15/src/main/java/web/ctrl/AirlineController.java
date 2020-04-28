package web.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.AirlineDao;
import flights.Airline;

@Controller
public class AirlineController {
	
	private static Logger log = LoggerFactory.getLogger(AirlineController.class);

	// inject dao
	@Autowired
	AirlineDao airlineDao;
	
	// map to URL "/airline/{id}"
	@RequestMapping(path="/airline/{id}") 
	public String findAirline( 
			// inject model
			// define id as a parameter from URL
			Model model,
			@PathVariable
			int id 
	) {
		log.debug("GET Airline info {}", id);
		Airline airline = airlineDao.find( id );
		
		// DEBUG
		System.out.println(airline);
		
		// use model to set airline attribute
		model.addAttribute( "airline", airline );
		return "airline-info";
	}
	
}
