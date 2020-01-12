<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="layout-header.jsp" %>

<div class="col-md-6 col-md-offset-3">
<h2>
  <span class="glyphicon glyphicon-plane" aria-hidden="true"></span>
  Airlines
</h2>

<table class="table table-bordered">
  <thead class="thead-dark">
    <tr>
      <th scope="col">id</th>
      <th scope="col">name</th>
      <th scope="col">alias</th>
      <th scope="col">iata</th>
      <th scope="col">icao</th>
      <th scope="col">callsign</th>
      <th scope="col">country</th>
      <th scope="col">active</th>
    </tr>
  </thead>
  <tbody> 
 	<c:forEach items="${ airlines }" var="airline">
	    <tr>
	      <td><c:out value="${ airline.airlineId }" /></td>
	      <td><c:out value="${ airline.name }" /></td>
	      <td><c:out value="${ airline.alias }" /></td>
	      <td><c:out value="${ airline.iata }" /></td>
	      <td><c:out value="${ airline.icao }" /></td>
	      <td><c:out value="${ airline.callsign }" /></td>
	      <td><c:out value="${ airline.country }" /></td>
	      <td><c:out value="${ airline.active }" /></td>
	    </tr>
	</c:forEach>
  </tbody>
</table>
</div>

<%@ include file="layout-footer.jsp" %>