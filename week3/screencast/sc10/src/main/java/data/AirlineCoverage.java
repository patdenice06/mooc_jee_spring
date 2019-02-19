package data;

public class AirlineCoverage {

	private	Long nbRoutes;
	private	String country;
	private String airline;
		
	public AirlineCoverage(Long nbRoutes, String country, String airline) {
		super();
		this.nbRoutes = nbRoutes;
		this.country = country;
		this.airline = airline;
	}
	
	
	public Long getNbRoutes() {
		return nbRoutes;
	}
	public void setNbRoutes(Long nbRoutes) {
		this.nbRoutes = nbRoutes;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
		
		
	
}
