<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="layout-header.jsp" %>

<div class="col-md-6 col-md-offset-3">
<h2>
  <span class="glyphicon glyphicon-plane" aria-hidden="true"></span>
  Airline Info
</h2>
<form>
  <div class="form-group">
    <label for="fId">Id</label>

    <input 
	  value=${ airline.airlineId }
	  type="text" class="form-control" id="fId" placeholder="Id">
  </div>
  <div class="form-group">
    <label for="fName">Name</label>
    <input 
	  value=${ airline.name }
	  type="text" class="form-control" id="fName" placeholder="Name">
  </div>
  <div class="form-group">
    <label for="fAlias">Alias</label>
    <input 
	  value=${ airline.alias }
	  type="text" class="form-control" id="fAlias" placeholder="Alias">
  </div>
  <div class="form-group">
    <label for="fCountry">Country</label>
    <input 
	  value=${ airline.country }
	  type="text" class="form-control" id="fCountry" placeholder="Country">
  </div>
  <div class="form-group">
    <label for="fCallsign">Callsign</label>
    <input 
	  value=${ airline.callsign }
	  type="text" class="form-control" id="fCallsign" placeholder="Callsign">
  </div>
  <div class="form-group col-md-6">
    <label for="fIata">IATA</label>
    <input 
	  value=${ airline.iata }
	  type="text" class="form-control" id="fIata" placeholder="iata code">
  </div>
  <div class="form-group col-md-6">
    <label for="fIcao">ICAO</label>
    <input 
	  value=${ airline.icao }
	  type="text" class="form-control" id="fIcao" placeholder="icao code">
  </div>
  <div class="checkbox">
    <label>
		<!--check the box if airline is active ! -->				
		<c:choose>
		    <c:when test="${ airline.active == 'true'}"> <input id="fActive" type="checkbox" checked="checked"/> Active </c:when>
		    <c:otherwise> <input id="fActive" type="checkbox" /> Active </c:otherwise>
		</c:choose>			
     </label>
  </div>
  
  <button type="submit" class="btn btn-primary">
    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
    Ok
  </button>
  
</form>
</div>
<%@ include file="layout-footer.jsp" %>



