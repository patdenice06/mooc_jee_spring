package flights;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-13T17:51:11.694+0100")
@StaticMetamodel(Route.class)
public class Route_ {
	public static volatile SingularAttribute<Route, RouteId> routeId;
	public static volatile SingularAttribute<Route, String> airlineCode;
	public static volatile SingularAttribute<Route, String> sourceCode;
	public static volatile SingularAttribute<Route, String> destinationCode;
	public static volatile SingularAttribute<Route, Boolean> codeshare;
	public static volatile SingularAttribute<Route, Short> stops;
	public static volatile SingularAttribute<Route, String> equipment;
}
