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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- Commen includes that have to be included on mulitple jsp pages.
	 ( Some only if the User is an Admin. ) -->

	<jsp:include page="navigation.jsp"></jsp:include>
	<jsp:include page="BootstrapModals/messageModal.jsp"></jsp:include>
	<jsp:include page="BootstrapModals/uploadModal.jsp"></jsp:include>
	
	<jsp:include page="BootstrapModals/deprecatedCourseModal.jsp"></jsp:include>
	
	<c:if test="${logedInPerson.getClass().name == 'de.dbae.administration.Admin'}">
		<jsp:include page="BootstrapModals/createCourseModal.jsp"></jsp:include>
		<jsp:include page="BootstrapModals/renameCourseModal.jsp"></jsp:include>
		<jsp:include page="BootstrapModals/deleteNutzerModal.jsp"></jsp:include>
		<jsp:include page="BootstrapModals/dokumenteFreigebenModal.jsp"></jsp:include>
		<jsp:include page="BootstrapModals/zahlungenEinsehenModal.jsp"></jsp:include>
	</c:if>

</body>
</html>