package data;

public class AirlineCoverage {

	Long nbRoutes;
	String country;
	String airlineName;
	
	public AirlineCoverage(Long nbRoutes, String country, String airlineName) {
		super();
		this.nbRoutes = nbRoutes;
		this.country = country;
		this.airlineName = airlineName;
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

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	
	
}
