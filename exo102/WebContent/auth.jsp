<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Authentication</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <!-- <link href="css/signin.css" rel="stylesheet"> -->
    <link href="css/auth.css" rel="stylesheet">

</head>
<body>

    <div class="container">
		<!-- Authentication failure -->
		<%
			String errorMessage = (String) request.getAttribute("errorMessage");
		   	if( errorMessage != null ){
		   		out.println("<div class=\"form-signin\">");
		   		out.println("<div class=\"alert alert-danger\" role=\"alert\">");
		   		out.println("<h5 class=\"form-signin-heading\">");
				out.println(errorMessage);
		   	}
		   	out.println("</h5>");
		   	out.println("</div>");
		   	out.println("</div>");
		%>
	
		<!-- Logout status -->
		<%
			String logoutMsg = (String) request.getAttribute("logoutMsg");
		   	if( logoutMsg != null ){
		   		out.println("<div class=\"form-signin\">");    	    
		   		out.println("<div class=\"alert alert-success\" role=\"alert\">");
				out.println("<h5 class=\"form-signin-heading\">");
				out.println(logoutMsg);
				out.println("</h5>");
				out.println("</div>");
				out.println("</div>");
		   	}
		%>	

      <form class="form-signin" method="post" action="auth">
        <h2 class="form-signin-heading">
		<!-- <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span> -->
		Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus name="login">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required name="password">
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>