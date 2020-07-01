package web.ctrl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.AirlineRepository;
import flights.Airline;

@Controller
public class AirlineController {
	
	private static Logger log = LoggerFactory.getLogger(AirlineController.class);

	// TODO : inject an AirlineRepository
	@Autowired
	AirlineRepository dao;
	
	@RequestMapping("/airline/{id}")
	public String findAirline(Model model, @PathVariable int id) {
		log.debug("GET Airline info {}", id);
		
		// TODO : Use repository to get airline
		Airline airline = dao.findById(id);
		
		model.addAttribute("airline", airline);
		return "airline-info";
	}
	
	@RequestMapping("/airlines")
	public String list(
			Model model,
			@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="") String country) 
	{
		log.debug("GET Airlines page {} for country {}", page, country);
		int size = 100;
		page--; // because spring pagination starts at 0.

		// TODO : use repository to get list of airline
		// Page<Airline> list = new PageImpl<>( Arrays.asList(new Airline()) );
		PageRequest pageable = new PageRequest(page, size);
		Page<Airline> list =
				country == null ||country.isEmpty() ?
						dao.findAll(pageable)
						: dao.findByCountry(country, pageable);
				
		model.addAttribute("list", list);

		// TODO : use repository to get countries
		// List<String> countries = Arrays.asList("France", "United States");
		List<String> countries = dao.selectDistinctCountries(); 
		model.addAttribute("countries", countries);
		
		return "airline-list";
	}
	
}
