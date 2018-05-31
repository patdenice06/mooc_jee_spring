<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="java.time.*" %>
<%@	page import="model.Persons" %>

<%
	List<Persons> persons = (List<Persons>) request.getAttribute("persons");
%>
   
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

    <title>Persons list</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
  </head>
  
<body>

	<div class = "container">
		<div class="panel panel-default">
			<!-- Default panel content -->
			<div class="panel-heading"> User(s) list</div>
			<div class="panel-body">
				<p>
					<!-- Show count of persons -->
					<%=persons.size()%> user(s)
				</p>
			</div>
							
			<!-- Table -->
			<table class ="table">
			<caption>List of user(s)</caption>
				<tr>
					<th>id</th>
					<th>registerDate</th>
					<th>email</th>
					<th>firstname</th>
					<th>lastname</th>
					<th>birthday</th>
					<th>age</th>
				</tr>
				
				<!-- Iterate through users -->	
				<%
						for (Persons person : persons) {
					%>
					<tr>
						<td><%=person.getId() %></td>
						<td><%=person.getRegisterDate() %></td>
						<td><%=person.getEmail() %></td>
						<td><%=person.getFirstName() %></td>
						<td><%=person.getLastName() %></td>
						<td><%=person.getBirthday() %></td>
						<td><%=person.getAge() %></td>
					</tr>
				<% } %>
			</table>		
						
		</div>
	</div> <!-- /container -->

</body>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
</html>