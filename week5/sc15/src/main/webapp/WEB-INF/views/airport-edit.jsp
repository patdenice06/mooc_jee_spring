<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="layout-header.jsp" %>

<div class="col-md-6 col-md-offset-3">
<h2>
  <span class="glyphicon glyphicon-road" aria-hidden="true"></span>
  Airport Edit
</h2>
<!--
TODO: Use taglib form to handle airport edition
      replace all form input with its taglib equivalent
-->
<form:form modelAttribute="airport">
  <div class="form-group">
    <label for="fId">Id</label>
	<!-- 
	TODO:
		id field shoud not be etitable,
		if the form is used to create a new airport, just don't show this field
	-->
      <%-- <form:input
       path="airportId"
       class="form-control" id="fId" disabled="true"/> --%>
      <form:input
       path="airportId"
       class="form-control" id="fId" readonly="true"/>
       

  </div>
  <div class="form-group">
    <label for="fName">Name</label>
	<form:input
	path="name"
	class="form-control" id="fName" placeholder="Name" />
   </div>
  <div class="form-group">
    <label for="fCity">City</label>
    <form:input
	   path="city"
	  class="form-control" id="fCity" placeholder="City" />
  </div>
  <div class="form-group">
    <label for="fCountry">Country</label>
    <form:input 
	  path="country"
	  class="form-control" id="fCountry" placeholder="Country" />
  </div>
  <div class="row">
	  <div class="form-group col-md-6">
		<label for="fIata">IATA</label>
		<form:input 
		  path="iata"
		  class="form-control" id="fIata" placeholder="iata code" />
	  </div>
	  <div class="form-group col-md-6">
		<label for="fIcao">ICAO</label>
		<form:input 
		  path="icao"
		  class="form-control" id="fIcao" placeholder="icao code" />
	  </div>
  </div>
  <div class="form-group">
    <label for="fLatitude">Latitude</label>
    <form:input 
	  path="latitude"
	  class="form-control" id="fLatitude" placeholder="(in decimal degree)" />
  </div>
  <div class="form-group">
    <label for="fLongitude">Longitude</label>
    <form:input 
	  path="longitude"
	  class="form-control" id="fLongitude" placeholder="(in decimal degree)" />
  </div>
  <div class="form-group">
    <label for="fAltitude">Altitude (ft)</label>
    <form:input 
	  path="altitude"
	  class="form-control" id="fAltitude" placeholder="(feet)" />
  </div>
  <div class="form-group">
    <label for="fTimezone">Timezone decay</label>
    <form:input 
	  path="timezone"
	  class="form-control" id="fTimezone" placeholder="(hours)" />
  </div>
  <div class="form-group">
    <label for="fTz">Timezone name</label>
    <form:input 
	  path="tzName"
	  class="form-control" id="fTz" placeholder="eg. Europe/Paris" />
  </div>
  <div class="form-group">
    <label for="fDaylight">Daylight saving time zone</label>
    <form:input 
	  path="daylight"
	  class="form-control" id="fDaylight" placeholder="" />
  </div>
  
  <button type="submit" class="btn btn-primary">
    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
    Ok
  </button>
  
</form:form>
</div>
<%@ include file="layout-footer.jsp" %>