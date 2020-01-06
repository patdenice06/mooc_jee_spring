package flights;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-13T17:51:11.428+0100")
@StaticMetamodel(Airline.class)
public class Airline_ {
	public static volatile SingularAttribute<Airline, Integer> id;
	public static volatile SingularAttribute<Airline, String> name;
	public static volatile SingularAttribute<Airline, String> alias;
	public static volatile SingularAttribute<Airline, String> iata;
	public static volatile SingularAttribute<Airline, String> icao;
	public static volatile SingularAttribute<Airline, String> callsign;
	public static volatile SingularAttribute<Airline, String> country;
	public static volatile SingularAttribute<Airline, Boolean> active;
}
