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
<%@ taglib prefix="m" uri="/WEB-INF/StarItemCourse.tld"%>
<c:set var="Admin" value="${logedInPerson.getClass().name == 'de.dbae.administration.Admin'}"></c:set>
<c:set var="Student" value="${logedInPerson.getClass().name == 'de.dbae.administration.Student'}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Overview</title>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
<!-- This Page displays all Courses stored in the Database. -->

	<jsp:include page="commenIncludes.jsp"></jsp:include>

	<div class="container">
		<div class="jumbotron">
		
		<!--  Add Switch View Button if the user is an Admin. -->
		
			<c:if test="${Admin == true || not empty tempAdmin}">
				<form action="SwitchView" method="get">
					<button type="submit" class="btn btn-primary pull-right">Switch
						View</button>
				</form>
			</c:if>

			<div class="page-header">
				<h3>Willkommen ${ username }</h3>
			</div>

			<!--  Main Panel that displays all the Courses stored in the Database. -->
			
			<div class="panel panel-primary">
				<div class="panel-heading">
					Kurs&uuml;bersicht
				</div>
				<div class="panel-body">
					<c:if test="${not empty courseList }">

						<div class="list-group">
						
						<!--  Loop that iterates over each Course stored in the session Attribute
							  "courseList". Firstly the Courses ID and Name are set as variables
							  to simply its access. Another Variable is the "Deprecated_Kurs" which
							  checks each course for being a deprecated course which are being displayed 
							  differently from the normal Courses. -->
						
							<c:forEach items="${courseList}" varStatus="loop">
							
							<c:set var="CourseID" value="${courseList[loop.index].kursId}"></c:set>
							<c:set var="CourseName" value="${courseList[loop.index].name}"></c:set>
							<c:set var="Deprecated_Kurs" value="${courseList[loop.index].getClass().name == 'de.dbae.administration.Deprecated_Kurs'}"></c:set>
							
								<c:choose>
									<c:when test="${Deprecated_Kurs == true}">
										<li class="list-group-item list-group-item-warning">
									</c:when>
									<c:otherwise>
										<li class="list-group-item">
									</c:otherwise>
								</c:choose>
								
								<!-- Clicking on this Link opens the "displayCourse.jsp" after loading all Documents
									using the "LoadDokumente" Servlet -->
									
								<a id="courseLink" class="courseLink" href="LoadDokumente?id=${CourseID}">${CourseName}</a>
								
								<!-- If the User is a Admin or a Premium Student a Star appears on the right side of
									each Row that opens up multiple additional Options:
									Premium-Student: Download entire Course
									Admin: Download entire Course, Delete Course, Rename Course and Remove Deprecation(
									if the Course id deprecated)-->
								
								<c:if test="${Admin == true || (Student == true && logedInPerson.premium == true)}">
									<div class="dropdown">
										<button class="btn btn-info dropdown-toggle btn-sm" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-star"></span></button>
										<ul class="dropdown-menu dropdown-menu-right adminDropdown">
											<li>
												<m:StarItemCourse type="download" id="${CourseID}" name="${CourseName}"/>
											</li> 
											<c:if test="${Admin == true}">
												<li class="divider" style="background-color: white;height: 6px;margin: 0px;"></li>
												
												<li>
													<m:StarItemCourse type="delete" id="${CourseID}"/>
												</li> 
												
												<li class="divider" style="background-color: white;height: 6px;margin: 0px;"></li>
												
												<li>
													<m:StarItemCourse type="rename" id="${CourseID}"/>
												</li>
												
												<c:if test="${Deprecated_Kurs == true}">
													<li class="divider" style="background-color: white;height: 6px;margin: 0px;"></li>
													<li>
														<m:StarItemCourse type="notDeprecated" id="${CourseID}"/>
													</li>
												</c:if>
												
											</c:if>
										</ul>
									</div>
								</c:if>


								<!-- Shows either: a Button to mark a Course as Deprecated which opens up a Modal
									to do exactly this or, if the Course was already marked, shows a Button that
									displays the Reason for the Deprecation and the Person who marked it.
								 -->
								<c:choose>
									<c:when test="${Deprecated_Kurs == false}">
										<button type="button"
											data-id="${CourseID}"
											data-toggle="modal" data-target="#deprecatedCourseModal"
											class="btn btn-info pull-right btn-xs open_modal_rename marginLeftBtn">
											<span class="glyphicon glyphicon-thumbs-down"></span>
											Deprecated
										</button>
									</c:when>
									<c:otherwise>
										<button href="#" data-toggle="popover" data-html="true"
											title="<b>Markierer:</b> ${courseList[loop.index].markierer}"
											data-placement="left" data-trigger="focus"
											data-content="<b>Grund:</b> ${courseList[loop.index].grund}"
											type="button" data-id="${CourseID}"
											class="btn btn-info pull-right btn-xs marginLeftBtn">
											<span class="glyphicon glyphicon-pushpin"></span> Show State
										</button>
									</c:otherwise>
								</c:choose>
								</li>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>
			<!--  Includes the Advertisement and the "Upgrade Account Button"
			 if the User is not an Admin or has a Premium Account. -->
			 <div style="margin-bottom:40px;">
			<c:if test="${Student == true && logedInPerson.premium == false }">
					<jsp:include page="BootstrapModals/upgradeAccountModal.jsp"></jsp:include>
					<button data-toggle="modal" data-target="#upgradeAccountModal"
						type="button" class="btn btn-primary pull-left">Buy
						Premium Account</button>
					
				</c:if>
				<c:if test="${Admin == false }">
				<button data-toggle="modal" data-target="#contactAdmin" style="margin-left:10px;"
						type="button" class="btn btn-primary pull-left">Contact Admin</button>
						<jsp:include page="BootstrapModals/contactAdminModal.jsp"></jsp:include>
				</c:if>
				</div>
		</div>
		
	</div>
	<c:if test="${Student == true && logedInPerson.premium == false }">
	<jsp:include page="advertisment.jsp"></jsp:include>
	</c:if>

<script type="text/javascript" src="js/loadPopover.js"></script>
	
</body>
</html>