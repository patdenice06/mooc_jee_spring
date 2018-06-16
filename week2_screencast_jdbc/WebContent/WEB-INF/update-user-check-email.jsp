<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>Update user account</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
  </head>
  
<body>

   	<c:set var = "form" scope="page"  value='<%= request.getAttribute("ATT_FORM")%>'></c:set> 
   	<%-- <c:set var = "user" scope="page"  value='<%= request.getAttribute("ATT_USER")%>'></c:set> --%> 

    <div class="container">    
    	<form method="post"  class="form-signin" action="update-user">
	        <h2 class="form-signin-heading">
			<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
			To change your account settings, enter your email</h2>	        
			<label for="inputEmail" class="sr-only">
			Email address. Please enter email</label>
	        <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
			<div class="checkbox">
	          <label>
	            <input type="checkbox" value="remember-me"> 
				Remember me
	          </label>
	        </div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">
			Update</button>    		
    	</form>
    	
		<!-- Display deleteting status message -->
		<c:if test="${ !empty form.errors  }">
			<p class="text-danger"><c:out value="${ form.errors.inputEmail }"></c:out></p>
		</c:if>		
		<c:if test="${ empty form.errors  }">
			<p class="text-success"><c:out value="${ form.result }"></c:out></p>			
		</c:if>
    	    	
    </div> <!-- /container -->
	


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>