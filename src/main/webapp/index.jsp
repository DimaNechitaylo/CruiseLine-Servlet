<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<jsp:include page="pages/header.jsp" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-2" >
				<h2>Filter</h2>
				<form action="/CruiseLine-Servlet/main?action=cruise_list"
					method="post">
					<div class="form-group">
						<label for="validationServer01">Date</label> <input type="date"
							name="date" class="form-control">
					</div>
					<div class="form-group">
						<label for="validationServer01">To</label> <input type="text"
							name="to" class="form-control">
					</div>
					<div class="form-group">
						<label for="validationServer01">Form</label> <input type="text"
							name="from" class="form-control">
					</div>
				</form>
			</div>
			<div class="col-md-8">
				<a href="/CruiseLine-Servlet/main?action=get_cruises"><h2>Crises list</h2></a>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">name</th>
							<th scope="col">ship</th>
							<th scope="col">passengersCount</th>
							<th scope="col">availableCount</th>
							<th scope="col">start</th>
							<th scope="col">finish</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cruise" items="${cruises}">
							<tr>
								<td><a href="/CruiseLine-Servlet/main?action=view_cruise&cruise_id=${cruise.id}">${cruise.name}</a></td>
								<td>${cruise.ship.name}</td>
								<td>${cruise.passengersCount}</td>
								<td>${cruise.availableCount}</td>
								<td>${cruise.start}</td>
								<td>${cruise.finish}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<footer>
	<jsp:include page="pages/footer.jsp" />
</footer>
</html>
