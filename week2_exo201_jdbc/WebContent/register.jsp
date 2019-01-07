<!DOCTYPE html>
<%@page import="user.User"%>
<html lang="en">
<% String ctxPath = request.getContextPath(); %>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Register now !</title>

  <link href="<%= ctxPath %>/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
  <link href="<%= ctxPath %>/css/register.css" rel="stylesheet">
</head>

<body>

<div class="container">

      <!-- TODO : fix form method -->
      <form class="form-signin" method="post" action="register">
      
        <!-- TODO : check for error message and display this div -->        
			  <% String errorSource = (String) request.getAttribute("errorSource");
			     String errorMessage = (String) request.getAttribute("errorMessage");
			     if (errorMessage != null) { %>
			         <div class="alert alert-danger" role="alert"><%= errorMessage %></div>
			  <% } %>		
		
			<!-- Logout status -->
			<%
				String logoutMsg = (String) request.getAttribute("logoutMsg");
			   	if( logoutMsg != null ){
			 %>			   		
					<div class="form-signin">
						<div class="alert alert-success" role="alert"><% out.println(logoutMsg); %>
							<h5 class="form-signin-heading\"></h5>
						</div>					
					</div>
			<% } %>	      
            
        <h2 class="form-signin-heading">
        	<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
        	Register Now !
        </h2>
        
        <div class="form-group <%= "firstname".equals(errorSource) ? "has-error" : "" %>">
			<label for="inputFirstName" class="sr-only">Firstname</label>  
			<input id="inputFirstName" class="form-control" placeholder="firstname" autofocus name="firstname" type="text">						         	
        </div>

		
        <div class="form-group <%= "lastname".equals(errorSource) ? "has-error"  : "" %>">
	        <label for="inputLastName" class="sr-only">Lastname</label>
	        <input id="inputLastName" class="form-control" placeholder="lastname" name="lastname" type="text">
        </div>
        
        <div class="form-group <%= "email".equals(errorSource) ? "has-error" : "" %>">
	        <label for="inputEmail" class="sr-only">Email address</label>
	        <input id="inputEmail" class="form-control" placeholder="Email address" name="email" type="email">
        </div>
        
        <div class="form-group <%= "password".equals(errorSource) ? "has-error" : "" %>">
	        <label for="inputPassword" class="sr-only">Password</label>
	        <input id="inputPassword" class="form-control" placeholder="Password" name="password" type="password">
        </div>
        
        <div class="form-group <%= "confirmPassword".equals(errorSource) ? "has-error" : "" %>">
	        <label for="inputPasswordConfirm" class="sr-only">Confirm Password</label>
	        <input id="inputPasswordConfirm" class="form-control" placeholder="confirm password" name="confirmPassword" type="password">
        </div>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Register !</button>
      </form>

</div>


  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="<%= ctxPath %>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>