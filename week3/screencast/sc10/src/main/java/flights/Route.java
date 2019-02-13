package flights;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Route implements Serializable {
	private static final long serialVersionUID = -2403746842148223177L;

	/**
	 * Airline 	2-letter (IATA) or 3-letter (ICAO) code of the airline.
	 */
	private String airlineCode;
	
	/**
	 * Airline ID 	Unique OpenFlights identifier for airline (see Airline).
	 */
	@ManyToOne
	@Id
	private Airline airline;
	
	/**
	 * Source airport 	3-letter (IATA) or 4-letter (ICAO) code of the source airport.
	 */
	private String sourceCode;
	
	/**
	 * Source airport ID 	Unique OpenFlights identifier for source airport (see Airport)
	 */
	@ManyToOne
	@Id
	private Airport source;
	
	/**
	 * Destination airport 	3-letter (IATA) or 4-letter (ICAO) code of the destination airport.
	 */
	private String destinationCode;
	
	/**
	 * Destination airport ID 	Unique OpenFlights identifier for destination airport (see Airport)
	 */
	@ManyToOne
	@Id
	private Airport destination;

	/**
	 * Codeshare 	"Y" if this flight is a codeshare (that is, not operated by Airline, but another carrier), empty otherwise.
	 */
	private Boolean codeshare;
	
	/**
	 * Stops 	Number of stops on this flight ("0" for direct)
	 */
	private Short stops;
	
	/**
	 * Equipment 	3-letter codes for plane type(s) generally used on this flight, separated by spaces
	 */
	private String equipment;

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public Boolean getCodeshare() {
		return codeshare;
	}

	public void setCodeshare(Boolean codeshare) {
		this.codeshare = codeshare;
	}

	public Short getStops() {
		return stops;
	}

	public void setStops(Short stops) {
		this.stops = stops;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	
	
}
