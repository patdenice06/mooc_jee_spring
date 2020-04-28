<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<form>


  <div class="form-group">
    <label for="fId">Id</label>
	<!-- 
	TODO:
		id field shoud not be etitable,
		if the form is used to create a new airport, just don't show this field
	-->
	
      <input type="text" value="xxx" class="form-control" id="fId" disabled/>

  </div>
  <div class="form-group">
    <label for="fName">Name</label>
    <input 
	  type="text"
	  class="form-control" id="fName" placeholder="Name" />
  </div>
  <div class="form-group">
    <label for="fCity">City</label>
    <input 
	  type="text"
	  class="form-control" id="fCity" placeholder="City" />
  </div>
  <div class="form-group">
    <label for="fCountry">Country</label>
    <input 
	  type="text"
	  class="form-control" id="fCountry" placeholder="Country" />
  </div>
  <div class="row">
	  <div class="form-group col-md-6">
		<label for="fIata">IATA</label>
		<input 
		  type="text"
		  class="form-control" id="fIata" placeholder="iata code" />
	  </div>
	  <div class="form-group col-md-6">
		<label for="fIcao">ICAO</label>
		<input 
		  type="text"
		  class="form-control" id="fIcao" placeholder="icao code" />
	  </div>
  </div>
  <div class="form-group">
    <label for="fLatitude">Latitude</label>
    <input 
	  type="text"
	  class="form-control" id="fLatitude" placeholder="(in decimal degree)" />
  </div>
  <div class="form-group">
    <label for="fLongitude">Longitude</label>
    <input 
	  type="text"
	  class="form-control" id="fLongitude" placeholder="(in decimal degree)" />
  </div>
  <div class="form-group">
    <label for="fAltitude">Altitude (ft)</label>
    <input 
	  type="text"
	  class="form-control" id="fAltitude" placeholder="(feet)" />
  </div>
  <div class="form-group">
    <label for="fTimezone">Timezone decay</label>
    <input 
	  type="text"
	  class="form-control" id="fTimezone" placeholder="(hours)" />
  </div>
  <div class="form-group">
    <label for="fTz">Timezone name</label>
    <input 
	  type="text"
	  class="form-control" id="fTz" placeholder="eg. Europe/Paris" />
  </div>
  <div class="form-group">
    <label for="fDaylight">Daylight saving time zone</label>
    <input 
	  type="text"
	  class="form-control" id="fDaylight" placeholder="" />
  </div>
  
  <button type="submit" class="btn btn-primary">
    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
    Ok
  </button>
  
</form>
</div>
<%@ include file="layout-footer.jsp" %>