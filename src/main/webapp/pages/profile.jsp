<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>HOME</title>
<jsp:include page="header.jsp"/>
</head>
<body>
Profile
	  <c:out value="${user.username}"/>
</body>
<footer>
	<jsp:include page="footer.jsp"/>
</footer>
</html>