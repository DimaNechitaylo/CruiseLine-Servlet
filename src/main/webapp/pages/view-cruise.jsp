<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<jsp:include page="header.jsp" />
</head>
</head>

<title>Insert title here</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>Сruise name </th>
			<th><c:out value="${cruise.name}" /></th>
		</tr>
		<tr>
			<th>Ship name</th>
			<th><c:out value="${cruise.ship.name}" /></th>
		</tr>

		<tr>
			<th>Start</th>
			<th><c:out value="${cruise.start}" /></th>
		</tr>
		<tr>
			<th>Finish</th>
			<th><c:out value="${cruise.finish}" /></th>

		</tr>
		<tr>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Ports</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="portName" items="${cruise.portNames}">
						<tr>
							<td>${portName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</tr>
		<tr>
			<th>Status</th>
			<th><c:out value="${order.status}" /></th>
		</tr>
	</table>
</body>
</html>