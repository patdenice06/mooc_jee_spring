package flights;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable 
public class RouteId implements Serializable {
	private static final long serialVersionUID = 8665028442166575885L;


	public RouteId() {
	}
	
	public RouteId(Airline airline, Airport source, Airport destination) {
		this.airline = airline;
		this.source = source;
		this.destination = destination;
	}
	
	/**
	 * Airline ID 	Unique OpenFlights identifier for airline (see Airline).
	 */
	@ManyToOne
	private Airline airline;
	

	/**
	 * Source airport ID 	Unique OpenFlights identifier for source airport (see Airport)
	 */
	@ManyToOne
	private Airport source;
	
	/**
	 * Destination airport ID 	Unique OpenFlights identifier for destination airport (see Airport)
	 */
	@ManyToOne
	private Airport destination;
	
	
	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}  
    	
	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash( airline, source, destination );
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( !(obj instanceof RouteId) ) return false;
		RouteId id = (RouteId) obj;
		return Objects.equals(id.airline, airline)
				&& Objects.equals(id.destination, destination)
				&& Objects.equals(id.source, source);
	}
}