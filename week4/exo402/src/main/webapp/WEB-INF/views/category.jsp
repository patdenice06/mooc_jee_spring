<%@ page pageEncoding="UTF-8" %>
<%@include file="_header.jsp" %>
<%-- import required classes --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<ul class="articles">
<%-- Iterate through articles ... --%>
	<c:forEach items="${articles}" var="article">
		<li>
			<a href="#">
				<span class="price">
					<%-- show price as X,XX --%>
<%-- 					<c:set var="price" value="${ article.price / 100}"></c:set>
					<c:set var = "finalPrice" value = "${fn:replace(price, '.', ',')}" />
					<c:out value="${ finalPrice }"></c:out> &euro;</span>
 --%>					
					<%-- <c:out value="${ finalPrice }"></c:out> &euro;</span> --%>
					<%-- <fmt:formatNumber type = "CURRENCY" maxIntegerDigits = "2" value = "${ article.price / 100 }" /> &euro;</span> --%>
					<fmt:setLocale value = "fr_FR"/>
					<fmt:formatNumber type = "CURRENCY" currencySymbol="&euro;" value = "${ article.price / 100 }"/></span>
					
				<%-- 
					show product image, you can use 'https://static1.chronodrive.com'
					as base URL and img path to complete the image URL
					==> Ex: https://www.chronodrive.com/img/PM/P/0/77/0P_339677.gif 
				--%>				
				<img src="https://www.chronodrive.com${ article.img }"/><br/>
				<%-- show product name --%>
				<c:out value="${ article.name }"></c:out> <br/>
			</a>
		</li>
	</c:forEach>
</ul>

<%@include file="_footer.jsp" %>