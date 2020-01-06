package flights;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-13T17:51:11.696+0100")
@StaticMetamodel(RouteId.class)
public class RouteId_ {
	public static volatile SingularAttribute<RouteId, Airline> airline;
	public static volatile SingularAttribute<RouteId, Airport> source;
	public static volatile SingularAttribute<RouteId, Airport> destination;
}
