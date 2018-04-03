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

    <title>Saisie coordonnées</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
</head>

<body>

    <div class="container">

      <form  method="post" class="form-signin">
        <h2 class="form-signin-heading">
		<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
		Coordonnées première ville</h2>
        <label for="inputLatitude" class="sr-only">Latitude</label>
        <input type="text"  name="p1lat" id="p1lat" class="form-control" placeholder="Latitude en degré décimal" required autofocus>
        <label for="inputLongitude" class="sr-only">Longitude</label>
        <input type="text"  name="p1lng" id="p1lng" class="form-control" placeholder="Longitude en degré décimal" required autofocus>
        
        <h2 class="form-signin-heading">
        <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
		Coordonnées deuxième ville</h2>
        <label for="inputLatitude" class="sr-only">Latitude</label>
        <input type="text"  name="p2lat" id="p2lat" class="form-control" placeholder="Latitude en degré décimal" required autofocus>
        <label for="inputLongitude" class="sr-only">Longitude</label>
        <input type="text"  name="p2lng" id="p2lng" class="form-control" placeholder="Longitude en degré décimal" required autofocus>        
        
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
				
    </div> <!-- /container -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>