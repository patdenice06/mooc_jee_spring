<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
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

    <title>Signup user</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
  </head>

  <body>
  
   	<c:set var = "form" scope="page"  value='<%= request.getAttribute("ATT_FORM")%>'></c:set> 
   	<c:set var = "user" scope="page"  value='<%= request.getAttribute("ATT_USER")%>'></c:set> 

    <div class="container">

      <form method="post"  class="form-signin" action="registration">
        <h2 class="form-signin-heading">
		<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
		Please sign up</h2>
        
		<label for="inputEmail" class="sr-only">
		Email address</label>
		<c:choose>
			<c:when test="${ empty user.email }">
			       <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
			</c:when>
			<c:otherwise>
			       <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="<c:out value="${ user.email }"></c:out>" required autofocus>
			</c:otherwise>
		</c:choose>`	
		<c:if test="${ !empty form.errors.inputEmail  }">
			<p class="text-danger"><c:out value="${ form.errors.inputEmail }"></c:out></p>	
		</c:if>	        
          
                  
		<label for="inputPassword" class="sr-only">
		Password</label>
		<small id="passwordHelpBlock" class="form-text text-muted">
		  Your password must contains at least 6 characters long.
		</small>        
        <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
		<c:if test="${ !empty form.errors.inputPassword  }">
			<p class="text-danger"><c:out value="${ form.errors.inputPassword }"></c:out></p>	
		</c:if>	        
        

		<label for="inputConfirmPassword" class="sr-only">
		Confirm password</label>
        <input type="password" name="inputConfirmPassword" id="inputConfirmPassword" class="form-control" placeholder="Confirm password" required>
		<c:if test="${ !empty form.errors.inputConfirmPassword  }">
			<p class="text-danger"><c:out value="${ form.errors.inputConfirmPassword }"></c:out></p>	
		</c:if>	        


		<label for="inputFirstname" class="sr-only">
		Firstname</label>
        <input type="text" name="inputFirstname" id="inputFirstname" class="form-control" placeholder="Firstname" required>
        
		<label for="inputLastname" class="sr-only">
		Lastname</label>
        <input type="text"  name="inputLastname" id="inputLastname" class="form-control" placeholder="Lastname" required>

 		<small id="birthdayHelpBlock" class="form-text text-muted">
		  <b>Birthday</b>
		</small>         		
 		<label for="inputBirthday" class="sr-only">
		Birthday</label>		
        <input type="date" name="inputBirthday" id="inputBirthday" class="form-control date" required>
        
		<div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 
			Remember me
          </label>
        </div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">
		Register</button>
      </form>

		  <!-- Registration status messages -->
	  <c:if test="${ empty form.errors }">
	    <p class="text-success"><c:out value="${ form.result }"></c:out></p>
	  </c:if>
	  <c:if test="${ !empty form.errors }">
      	<p class="text-danger"><c:out value="${ form.result }"></c:out></p>  
      	<p class="text-danger"><c:out value="${ form.errors.inputEmail }"></c:out></p>  
	  </c:if>
		
    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
