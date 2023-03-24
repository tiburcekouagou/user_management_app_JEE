<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>User Management Application</title>
</head>

<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="assets/partials/header.jsp"%>
	<%@page
		import="bj.highfive.usermanagement.dao.UserDAO,bj.highfive.usermanagement.bean.*,java.util.*"%>

	<hr />

	<h1>Liste des utilisateurs</h1>

	<div class="container">
		<table class="table table-stripped">

			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Email</th>
					<th>Country</th>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${ users }">
					<tr>
						<td><c:out value="${ user.id }" /></td>
						<td><c:out value="${ user.name }" /></td>
						<td><c:out value="${ user.email }" /></td>
						<td><c:out value="${ user.country }" /></td>
						<td>
							<form method="post" action="edit">
								<input type="hidden" name="id" value="${user.id}" /> 
							    <input type="submit" value="Editer" />
							</form>
						</td>
						<td><a href="delete?id=${ user.id }"><c:out
									value="Supprimer" /></a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</div>

	<%@ include file="assets/partials/bootstrap-script.jsp"%>
</body>
</html>
