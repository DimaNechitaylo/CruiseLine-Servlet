<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<body>
	<h1>
		<a href="/CruiseLine-Servlet/"> Cruise Line</a>
	</h1>
	<c:choose>
		<c:when test="${user == null}">
			<a href="/CruiseLine-Servlet/pages/signup.jsp">sign Up</a>
			</br>
			<a href="/CruiseLine-Servlet/pages/signin.jsp">sign In</a>
		</c:when>
		<c:when test="${user != null}">
			<a href="/CruiseLine-Servlet/main?action=logout">logout</a>
			<a href="/CruiseLine-Servlet/main?action=profile"><h2>
					<c:out value="${user.username} ${user.id}" />
				</h2></a>
			<c:if test="${user.role == 'ADMIN'}">
				<a href="/CruiseLine-Servlet/pages/admin.jsp">admin</a>
			</c:if>
		</c:when>

	</c:choose>

	<hr size="10" color="blue">

</body>
</html>