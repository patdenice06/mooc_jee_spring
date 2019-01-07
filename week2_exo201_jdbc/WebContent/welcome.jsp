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

    <title>Welcome</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    <!-- <link href="css/auth.css" rel="stylesheet"> -->

</head>    

<body>

<%
// check if authenticated !
if (session.getAttribute("authenticate") == null || !session.getAttribute("authenticate").equals("OK")) {
    //   if not : show an error an a link to auth.jsp
    out.print("<h1> error not authenticate <h1> ");
    out.print("<a href='/week2_exo201_jdbc/auth.jsp' style='color: red; text-decoration: none'>RECONNECT</a>") ;
}
else {
    out.print("<h1>Welcome Admin !</h1>");  
    out.print("[ <a href='auth?logout' style='color: red; text-decoration: none'>DISCONNECT</a> ]");

}
%>

</body>	
</html>
