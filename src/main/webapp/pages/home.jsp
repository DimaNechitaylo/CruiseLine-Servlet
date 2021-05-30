<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>HOME</title>
Home
</head>
<body>
	<jsp:include page="header.jsp"/>
	<br/>
	Home page
	<c:if test='${request.getSession().getAttribute("user") == null}'>
    <%=request.getSession().getAttribute("user")%>
	</c:if>
	<c:if test='${request.getAttribute("user") != null}'>
    <p>Visible</p>
	</c:if>
	<jsp:include page="footer.jsp"/>

</body>
</html>