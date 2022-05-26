<%-- 
  - Author(s): Jonathan Lochmann, Maxim Shulyatev, Julius Bartels
  - Date:
  - Copyright Notice:
  - @(#)
  - Description: 
  --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<img id="imgID" src="img/logo.png">
	</div>
	<div class="container">
		<nav class="navbar navbar-inverse">
		<div class="container-fluid">

			<ul class="nav navbar-nav">
				<c:if test="${empty logedInPerson }">
					<li><a href="#">Home</a></li>
				</c:if>

				<c:if test="${not empty logedInPerson }">

					<li class="active"><a href="courseOverview.jsp">Kursübersicht</a></li>
					<!-- <li><a href="courseOverview.jsp">Mein Profil</a></li> -->
					<c:choose>
						<c:when
							test="${logedInPerson.getClass().name == 'de.dbae.administration.Admin' || logedInPerson.premium == true }">
							<li><a href="suche.jsp">Suche</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a>Suche</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>


				<c:if
					test="${logedInPerson.getClass().name == 'de.dbae.administration.Admin'}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						type="button" data-toggle="dropdown"> Admin Funktionen<span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#" data-toggle="modal"
								data-target="#newCourseModal">Neuer Kurs</a></li>
							<li><a href="#" data-target="#deleteNutzer" data-toggle="modal">Nutzer löschen</a></li>
							<li><a href="#" data-target="#dokumenteFreigeben" data-toggle="modal">Dokumente Freigeben</a></li>
							<li><a href="#" data-target="#zahlungenEinsehen" data-toggle="modal">Zahlungen Einsehen</a></li>
						</ul></li>
				</c:if>
			</ul>
			<c:choose>
				<c:when test="${empty logedInPerson }">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#" data-toggle="modal"
							data-target="#modalSignUp"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li><a href="#" data-toggle="modal" data-target="#modalLogin"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
						<p class="navbar-text">Signed in as ${username}</p>
						<li><form action="Logout" method="post">
								<button type="submit" class="btn transparentBtn navbar-btn">
									<span class="glyphicon glyphicon-log-out"></span>Logout
								</button>
							</form></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
		</nav>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

