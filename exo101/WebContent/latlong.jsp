<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    
	<title>Calculate distance and bearing between two Latitude/Longitude points using haversine formula in Java</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

</head>
<body>

<form method="post" name="ortho-dist" id="ortho-dist">
        <fieldset><legend>Great-circle distance between two towns on earth</legend>
            <p>Enter the co-ordinates into the text boxes to try out the calculations. Only one format is accepted:</p>
            <ul class="list">
                <li>signed decimal degrees without compass direction, where negative indicates
                    west/south (e.g. 40.7486, -73.9864):
                </li>
            </ul>
            <table class="inputs">
                <tbody>
                <tr>
                    <td>
                        <input name="town1" value="Lille" placeholder="Change with another town" required autofocus type="text">
                    </td>
                    <td>
                        <input name="p1lat" class="lat1" value="50.63722" placeholder="Latitude en degré décimal" required autofocus type="text">
                        <input name="p1lng" class="lon1" value="3.063333" placeholder="Longitude en degré décimal" required autofocus type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="town2" value="Marseille" placeholder="Change with another town" required autofocus type="text">
                    </td>
                    <td>
                        <input name="p2lat" class="lat2" value="43.29639" placeholder="Latitude en degré décimal" required autofocus type="text">
                        <input name="p2lng" class="lon2" value="5.37" placeholder="Longitude en degré décimal" required autofocus type="text">
                    </td>
                </tr>
            </tbody>
            </table>
            
<!--             <table class="outputs">
                <tbody>
                <tr>
                    <td>Distance:</td>
                    <td>
                        <output class="result-dist" title="Distance (in km)"> 968.9
                        <span title="rounding to 4 significant figures reflects the approx. 0.3% accuracy of the spherical model" class="small grey"> km (to 4 SF<sup>*</sup>)</span>
                        </output>
                    </td>
                </tr>
            </tbody>
            </table>
 -->            
        </fieldset>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Calcul distance</button>
    </form>

		<h2 class="form-signin-heading">
		<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
			<%
				String distance = (String) request.getAttribute("distance");
				if( distance != null ){
					out.print("Distance = " + distance + " km");
				}
			%>
		</h2>



    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
    
</body>
</html>