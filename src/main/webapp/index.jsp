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

	<h1>Bienvenue !</h1>

	<%@ include file="assets/partials/bootstrap-script.jsp"%>
</body>
</html>
