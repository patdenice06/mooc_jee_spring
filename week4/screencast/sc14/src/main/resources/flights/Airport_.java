package flights;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-13T17:51:11.691+0100")
@StaticMetamodel(Airport.class)
public class Airport_ {
	public static volatile SingularAttribute<Airport, Integer> id;
	public static volatile SingularAttribute<Airport, String> name;
	public static volatile SingularAttribute<Airport, String> city;
	public static volatile SingularAttribute<Airport, String> country;
	public static volatile SingularAttribute<Airport, String> iata;
	public static volatile SingularAttribute<Airport, String> icao;
	public static volatile SingularAttribute<Airport, Double> latitude;
	public static volatile SingularAttribute<Airport, Double> longitude;
	public static volatile SingularAttribute<Airport, Integer> altitude;
	public static volatile SingularAttribute<Airport, Float> timezone;
	public static volatile SingularAttribute<Airport, Character> daylight;
	public static volatile SingularAttribute<Airport, String> tzName;
	public static volatile SingularAttribute<Airport, String> source;
}
