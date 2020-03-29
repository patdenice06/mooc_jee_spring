package fr.eservices.drive.web;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.dao.StatusHistory;
import fr.eservices.drive.model.StatusHistoryEntity;
import fr.eservices.drive.model.TimeObject;


// define as a REST Controller in spring context
@RestController
@RequestMapping
public class RestHistoryController {
	
	// Inject reference from spring context
	@Autowired
	HistorySource historySource;

	// map this operation to GET only
	@GetMapping(path = "/history/{orderId}.json")
	public List<StatusHistory> getHistory( @PathVariable int orderId ) {
		return historySource.orderHistory(orderId);		
	}
	
	// map this operation to POST only
	@PostMapping( path = "/history/{orderId}.json")
	public String addStatus( @PathVariable int orderId, @RequestBody StatusHistory history ) {
		// try to add a status,
		// return "Ok" or "Error" if exception thrown 		
		try {
			historySource.addHistoryStatus(orderId, history);
		} catch (DataException e) {
			System.out.println( e.getMessage() );
			return "Error";
		}		
		return "Ok";
	}

	

	/**
	 * Get all status for a given order in JSON format 
	 * @param orderId ID of an order
	 * @return All the status from a given order id
	 */
	@GetMapping(
			path = "/history/all/{orderId}.json",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	public List<StatusHistoryEntity> getHistoryAll( @PathVariable int orderId ){
		List<StatusHistoryEntity> results = new ArrayList<StatusHistoryEntity>( Arrays.asList());;
		try {
			results = historySource.orderAllHistory(orderId);
		} catch (DataException e) {
			e.printStackTrace();
			// return "Error";
		}
		
		return results;
	}	
	
	
	@PostMapping(
				path = "/histories/{orderId}.json",
				consumes="application/json",
				produces="application/json")
	@ResponseBody
	public String addListStatus( @PathVariable int orderId, @RequestBody List<StatusHistory> histories ) {
		// try to add a list of status,
		// return "Ok" or "Error" if exception thrown 		
		
		// Set good orderId value to all status of the order
		for(StatusHistory statusHistory : histories) {
			statusHistory.setOrderId(orderId);
		}
		
		try {
			historySource.addHistoryAllStatusl(orderId, histories);
		} catch (DataException e) {
			System.out.println( e.getMessage() );
			return "Error";
		}		
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
	
	@GetMapping(path = "/getDate.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public TimeObject sentDateGet() {
		return getTime();
	}
	
	@PostMapping(path = "/postDate.json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	  public List<StatusHistory> testPost( @RequestBody StatusHistory statusHistory ) {
		List<StatusHistory> status = new ArrayList<StatusHistory>( Arrays.asList());
		status.add(statusHistory);
		return status;
	}
	
	
}
