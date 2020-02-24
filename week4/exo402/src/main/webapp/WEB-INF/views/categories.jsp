<%@ page pageEncoding="UTF-8" %>
<%@include file="_header.jsp" %>
<%-- import required classes --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="categories">
	<%-- iterate through categories --%>
	<c:forEach items="${categories}" var="categorie">
		<li>
			<%-- set a link to each category content --%>
			<a href="category/${ categorie.id }.html">
				<%-- 
					add an image related to category ID.
					Category images are located in /img/ and name catID.jpg (ID as 2 digits)
				 --%>
				<img src="<%= ctxPath %>/img/cat0${ categorie.id }.jpg"/><br/>
				<%-- Show category name --%>
				<c:out value="${ categorie.name }"></c:out>
			</a>
		</li>		
	</c:forEach>

</ul>

<%@include file="_footer.jsp" %>