<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="layout-header.jsp" %>
<div class="col-md-8 col-md-offset-2" style="border: solid 1px #666; background-color: #fff">
<h2>
  <span class="glyphicon glyphicon-plane" aria-hidden="true"></span>
  Airline List
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
		  <option value="">TOUS</option>
		  <c:forEach items="${countries}" var="country">
			<option>${country}</option>
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
	<th>#</th>
	<th>ICAO</th>
	<th>IATA</th>
	<th>Name</th>
	<th>Country</th>
  </tr>
  <c:forEach items="${list.content}" var="airline">
  <tr>
	<td><a href="airline/${airline.id}">${airline.id}</a></td>
	<td>${airline.icao}</td>
	<td>${airline.iata}</td>
	<td>${airline.name}</td>
	<td>${airline.country}</td>
  </tr>
  </c:forEach>
  
</table>

<nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="?page=1&country=${param.country}" aria-label="Begin">
        <span aria-hidden="true">|&laquo;</span>
      </a>
    </li>
	<c:forEach var="i" 
		begin="${list.number-10 > 0 ? list.number-10 : 1}" 
		end="${list.number+10 < list.totalPages ? list.number+10 : list.totalPages}">
    <li class="${ i == list.number+1 ? 'active' : ''}"><a href="?page=${i}&country=${param.country}">${i}</a></li>
	</c:forEach>
    <li>
      <a href="?page=${list.totalPages}&country=${param.country}" aria-label="End">
        <span aria-hidden="true">&raquo;|</span>
      </a>
    </li>
  </ul>
</nav>

</div>
<%@ include file="layout-footer.jsp" %>