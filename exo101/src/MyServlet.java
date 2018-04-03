import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dist")
public class MyServlet extends HttpServlet {

	/**
	 * Exercice: week1/exo101
	 * Servlet de calcul de	distance dans un référenSel	géodésique (wgs	84)		
	 */
	private static final long serialVersionUID = 1L;

	// @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// Show a HTML form with 4 input text (2 for each point)
		//        your input must be named 'p1lat', 'p1lng', 'p2lat', 'p2lng'
//		this.getServletContext().getRequestDispatcher("/saisieCoordonnees.jsp").forward(req, resp);
		this.getServletContext().getRequestDispatcher("/latlong.jsp").forward(req, resp);
	}
	
	// @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// Get first point latitude / longitude
		String p1lat = (String) req.getParameter("p1lat");
		String p1lng = (String) req.getParameter("p1lng");
		System.out.println("p1lat = " + p1lat);	// DEBUG
		System.out.println("p1lng = " + p1lng);	// DEBUG
		
		// Get second point latitude / longitude
		String p2lat = (String) req.getParameter("p2lat");
		String p2lng = (String) req.getParameter("p2lng");
		System.out.println("p2lat = " + p2lat);	// DEBUG
		System.out.println("p2lng = " + p2lng);	// DEBUG
		
		// Rem: le controle de la présence des 4 paramètres est efffectué automatiquement dans la page JSP
		
		// Compute distance between two points
//		En guise de contrôle, saisir les coordonnées suivantes :
//			* Lille : 50.63722, 3.063333 (exprimé en degré décimal)
//			* Marseille : 43.29639, 5.37
//			La distance retournée doit être : 834,7 km		
		double distance = computeDistance( new Double(p1lat), new Double(p1lng), new Double(p2lat), new Double(p2lng) );
		System.out.println("Distance = " + distance);	// DEBUG

		// For displaying with 1 decimal
		DecimalFormat df = new DecimalFormat("#.#");
		String distanceOneDecimal = df.format(distance);
		System.out.println("Distance one decimal = " + distanceOneDecimal);	// DEBUG
		
		req.setAttribute("distance", distanceOneDecimal);
		this.getServletContext().getRequestDispatcher("/latlong.jsp").forward(req, resp);
		
	}

	
	/**
	 * Compute distance between two points with the Haversine formula
	 */
	private double computeDistance(double p1lat, double p1lng, double p2lat, double p2lng) {
		double R = 6371.0;	// Earth radius in km
		double phi1 = Math.toRadians(p1lat) ;
		double phi2 = Math.toRadians(p2lat) ;
		double deltaPhi = Math.toRadians( p2lat - p1lat );
		double deltaLambda = Math.toRadians( p2lng - p1lng );
		
		// Haversine formula: 	a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
		double a = Math.sin( deltaPhi / 2 ) * Math.sin( deltaPhi / 2 ) +
				   Math.cos( phi1 ) *  	Math.cos( phi2 ) *
				   Math.sin( deltaLambda / 2 ) * Math.sin( deltaLambda / 2 );
		// c = 2 ⋅ atan2( √a, √(1−a) )
		double c = 2 * Math.atan2( Math.sqrt(a),  Math.sqrt(1-a) );
		
		// Distance: d = R ⋅ c		
		return R * c ;
	}

}