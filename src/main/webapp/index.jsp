<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<html>
<head>
<jsp:include page="pages/header.jsp" />
</head>
<body>
	<div class="filter">
	<h2>Filter</h2>
	<form action = "/CruiseLine-Servlet/main?action=cruise_list" method="post">
		Date <input type="date" name = "date">
		</br>
		To <input type="text" name = "to">
		</br>
		Form <input type="text" name = "from">
		</br>
		<input type="submit" value="send">
	</form>
	</div>
	<% 
	      List<String> cruises = (List<String>) request.getSession().getAttribute("cruises");
	      pageContext.setAttribute("cruises", cruises);
	 %>
	<div class="crises_list">
	    <a href="/CruiseLine-Servlet/main?action=get_cruises">crises_list</a>
		
		<c:forEach var="cruise" items="${cruises}">
			<p>${cruise}</p>
		</c:forEach>
		<table>
			<tr>
				<td>...</td>
				<td>...</td>
				<td>...</td>
			</tr>
			<tr>
				<td>...</td>
				<td>...</td>
				<td>...</td>
			</tr>
		</table>
	</div>
</body>
<footer>
	<jsp:include page="pages/footer.jsp" />
</footer>
</html>
