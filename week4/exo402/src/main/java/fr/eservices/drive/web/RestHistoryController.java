package fr.eservices.drive.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eservices.drive.dao.CatalogDao;
import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.dao.StatusHistory;
import fr.eservices.drive.model.TimeObject;


// define as a REST Controller in spring context
@RestController
@RequestMapping
public class RestHistoryController {
	
	// Inject reference from spring context
	@Autowired
	HistorySource historySource;
	
	// For testing
	@Autowired
	CatalogDao dao;


	// map this operation to GET only
	@GetMapping(path = "/history/{orderId}.json")
	public List<StatusHistory> getHistory( @PathVariable int orderId ) {
		return historySource.orderHistory(orderId);		
	}
	
	// map this operation to POST only
	@PostMapping(path = "/history/{orderId}.json")
	public String addStatus( @PathVariable int orderId, @RequestBody StatusHistory history ) {
		// try to add a status,
		try {
			historySource.addHistoryStatus(orderId, history);
		} catch (DataException e) {
			System.out.println( e.getMessage() );
			return "Error";
		}		
		// return "Ok" or "Error" if exception thrown 		
		return "Ok";
	}
	
	
	private TimeObject getTime() {
		TimeObject t = new TimeObject();
		Date now = new Date();
		t.day = DateFormat.getDateInstance(DateFormat.SHORT).format(now);
		t.time = DateFormat.getTimeInstance(DateFormat.SHORT).format(now);
		t.locale = Locale.getDefault().toString();
		t.timestamp = (long) (now.getTime() / 1000);
		return t;
	}	
	
	
	/*
	 * For testing
	 */
	
	@GetMapping(path = "/date.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public TimeObject sentDate() throws DataException {
		return getTime();
	}
	
	

}
