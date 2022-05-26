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
<title>Index</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="navigation.jsp"></jsp:include>
			<div class="container">
				<div class="jumbotron">
					<div class="page-header">
						<h3>Sie befinden sich im Moment auf dem öffentlichen Bereich
							der Website.</h3>
					</div>
					<h4>Loggen Sie sich ein oder erstellen Sie einen Account um
						den Service zu benutzen.</h4>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="BootstrapModals/signUp.jsp"></jsp:include>
	<jsp:include page="BootstrapModals/login.jsp"></jsp:include>
	<jsp:include page="BootstrapModals/messageModal.jsp"></jsp:include>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		

</body>
</html>