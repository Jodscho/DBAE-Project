<%-- 
  - Author(s): Jonathan Lochmann, Maxim Shulyatev, Julius Bartels
  - Date:
  - Copyright Notice:
  - @(#)
  - Description: 
  --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/WEB-INF/search.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>

	<jsp:include page="commenIncludes.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="container">
			<div class="jumbotron">
				<div class="page-header">
					<h4>Geben Sie beliebige Suchwörter ein um die Datenbank 
					nach Dokumenten zu durchsuchen, welche diese enthalten.
					</h4>
				</div>
				
				<div class="form-group row">
					<div class="col-lg-5 col-md-6 col-sm-6 col-xs-12">
						<s:getSearch></s:getSearch>
					</div>
				</div>
				<!-- searchStr wird in SearchServlet gesetzt -->	
				<c:if test="${ not empty searchStr }">
					<jsp:include page="displayCourse.jsp"></jsp:include>
				</c:if>
					
			</div>
		</div>
	</div>



</body>
</html>