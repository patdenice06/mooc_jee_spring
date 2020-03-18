<!DOCTYPE html>
<html lang="en">
<%
String ctxPath = request.getContextPath();
%>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%= ctxPath %>/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="<%= ctxPath %>/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%= ctxPath %>/resources//css/signin.css" rel="stylesheet">
  </head>

  <body>
    <div class="container">
    
	    <div class="panel panel-default">
	    	<!-- Default panel contents -->
	    	<div class="panel-heading">Panel heading</div>
	    	
	    	Message is : ${message}
	    	
	    	
	    </div>
    
    </div> <!-- /container -->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%= ctxPath %>/resources/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>