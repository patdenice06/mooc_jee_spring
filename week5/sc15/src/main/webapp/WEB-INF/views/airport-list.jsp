<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="layout-header.jsp" %>
<div class="col-md-8 col-md-offset-2" style="border: solid 1px #666; background-color: #fff">
<h2>
  <span class="glyphicon glyphicon-road" aria-hidden="true"></span>
  Airport List
  <a class="btn btn-primary" style="float: right;" href="#link_to_edit" role="button">
    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
	New
  </a>
</h2>
<div class="row" style="margin: 5px">
	<form class="form-inline">
	  <div class="form-group">
		<label for="fCountry">Country</label>
		  <select class="form-control" id="fCountry" name="country">
			  <!-- iterate through countries and add an option for each -->
			  <c:forEach items="${countries}" var="aCountry">
				<option>${aCountry}</option>
			  </c:forEach>
		  </select>
	  </div>
	  <button type="submit" class="btn btn-default">
	    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
	    filter
	  </button>
	</form>
</div>

<table class="table table-bordered table-striped table-hover">
  <tr>
	<th>id</th>
	<th>ICAO</th>
	<th>IATA</th>
	<th>Name</th>
	<th>City</th>
  </tr>
  <!-- iterate through airports and show each one in a row -->
  <c:forEach items="${airports}" var="airport">
	  <tr>
		<td><a href="airport/${airport.airportId}">${airport.airportId}</a></td>
		<td>${airport.icao}</td>
		<td>${airport.iata}</td>
		<td>${airport.name}</td>
		<td>${airport.city}</td>
	  </tr>
  </c:forEach>
</table>

</div>
<%@ include file="layout-footer.jsp" %>