package spring;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.DatatypeConverter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.eservices.drive.dao.HistorySourceJpaImpl;
import fr.eservices.drive.dao.Status;
import fr.eservices.drive.dao.StatusHistory;
import fr.eservices.drive.model.StatusHistoryEntity;
import fr.eservices.drive.model.TimeObject;
import fr.eservices.drive.web.RestHistoryController;
import h2.H2jdbcCloseConnection;
import web.WebTool;

public class RestHistoryControllerTest {
	
	ConfigurableApplicationContext ctx;
	WebTool wt;
	ObjectMapper mapper;


	@Before
	public void setupSpringContext() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ConfigurableListableBeanFactory factory = ctx.getBeanFactory(); 
		factory.registerSingleton("daoHistorySource", new HistorySourceJpaImpl());
		ctx.register(RestHistoryController.class);
		ctx.refresh();
		
		this.ctx = ctx;
		
		// Init WebTool
		String p = System.getProperty("usePort");
		if ( p == null ) p = "8080";
		wt = new WebTool("localhost:"+p, "/exo402");
	}
	
	@After
	public void closeContext() {
		H2jdbcCloseConnection.close();
		ctx.close();
	}	
	

	@Test
	public void testAddStatus() throws IOException {
		StatusHistory statusHistory = new StatusHistory();
		statusHistory = createStatus("ORDERED", "2017-11-28T10:00:00Z");
		
		mapper = new ObjectMapper();
		// Java object to JSON data in a file
		mapper.writeValue(new File("data/status.json"), statusHistory);	// Just for test
		// Java object to JSON string
		String jsonString = mapper.writeValueAsString(statusHistory);
				
		wt.setUrlEncoded("application/json");
		wt.post( "/history/1.json", jsonString );
	}

	
	@Test
	public void testGetHistory() throws IOException {		
		InputStream in = wt.get("/history/1.json");
		mapper = new ObjectMapper();
		TypeReference<List<StatusHistory>> typeReference = new TypeReference<List<StatusHistory>>() {};
		List<StatusHistory> statusHistories = mapper.readValue(in, typeReference);
		Assert.assertTrue( "Check status failed.", checkStatus(statusHistories.get(0), 1, "ORDERED", "28 nov. 2017 11:00:00") );
	}	
	

	
	@Test
	public void testAddListStatus() throws IOException {	
		List<StatusHistory> statusHistories = new ArrayList<StatusHistory>( Arrays.asList());
		statusHistories.add( createStatus("ORDERED", "2017-11-28T10:00:00Z") );
		statusHistories.add( createStatus("READY_TO_DELIVER", "2017-11-28T10:00:02Z") );
		statusHistories.add( createStatus("DELIVERED", "2017-11-28T10:00:04Z") );
		
		mapper = new ObjectMapper();
		String jsonArrayString = mapper.writeValueAsString(statusHistories);
		
		wt.setUrlEncoded("application/json");
		wt.post( "/histories/2.json", jsonArrayString );
	}	
	
	

 	@Test
	public void testGetHistoryAll() throws IOException {
		InputStream in = wt.get("/history/all/2.json");		
		mapper = new ObjectMapper();
		TypeReference<List<StatusHistoryEntity>> typeReference = new TypeReference<List<StatusHistoryEntity>>() { };
		List<StatusHistoryEntity> statusHistoriesEntities = mapper.readValue(in, typeReference);
		
		Assert.assertTrue( "Check status failed.", checkStatus(statusHistoriesEntities.get(0), 2, "ORDERED", "Tue Nov 28 11:00:00 CET 2017") );
		Assert.assertTrue( "Check status failed.", checkStatus(statusHistoriesEntities.get(1), 2, "READY_TO_DELIVER", "Tue Nov 28 11:00:02 CET 2017") );
		Assert.assertTrue( "Check status failed.", checkStatus(statusHistoriesEntities.get(2), 2, "DELIVERED", "Tue Nov 28 11:00:04 CET 2017") );
	}

 	
	private StatusHistory createStatus(String statusName, String dateTime) {
		StatusHistory status = new StatusHistory();
		status.setStatus( Status.valueOf(statusName) );
		status.setStatusDate( DatatypeConverter.parseDate(dateTime).getTime() );
		TimeObject t = new TimeObject();
		Date now = new Date();
		t.day = DateFormat.getDateInstance(DateFormat.SHORT).format(now);
		t.time = DateFormat.getTimeInstance(DateFormat.SHORT).format(now);
		t.locale = Locale.getDefault().toString();
		t.timestamp = (long) (now.getTime() / 1000);
		status.setTimeObject(t);
		return status;		
	}
	
	private boolean checkStatus(StatusHistory statusHsitory, int orderId, String status, String date) {
		return (statusHsitory.getOrderId() == orderId) &&
				(statusHsitory.getStatus().name() == status) &&
				(statusHsitory.getStatusDate().toLocaleString().toString().equals(date));
	}
 	

	private boolean checkStatus(StatusHistoryEntity statusHistoryEntity, int orderId, String status, String date) {
		return statusHistoryEntity.getOrderId() == orderId &&
			   statusHistoryEntity.getStatus().equals(status) &&
			   date.equals( statusHistoryEntity.getStatusDate().toString() );
	}

}
