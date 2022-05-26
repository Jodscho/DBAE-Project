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
<%@ taglib prefix="u" uri="/WEB-INF/getFilter.tld" %>
<%@ taglib prefix="d" uri="/WEB-INF/DocumentButton.tld"%>
<%@ taglib prefix="p" uri="/WEB-INF/DocumentPopover.tld"%>
<c:set var="Admin"
    value="${logedInPerson.getClass().name == 'de.dbae.administration.Admin'}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Display</title>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
<!-- This jsp is responsible for displaying all Documents stored in a particular Course
    as well as the Results of a Search from a Admin or a Premium User. -->
    
<!--  If the "kurs" attribute is empty the paged was called by the search servlet. The
    following variables are set accordingly. -->
<c:choose>
	<c:when test="${not empty kurs}">
		<c:set var="Panelheader" value="${kurs.name}"></c:set>
		<c:set var="EmptyMessage" value="Dieser Kurs hat noch keine	Dokumente."></c:set>
		<c:if test="${ filter == true }">
			<c:set var="EmptyMessage" value="Es wurden keine Dokumente mit dem Filter gefunden."></c:set>
		</c:if>
	</c:when>
	
	<c:otherwise>
		<c:set var="Panelheader" value="Suchergebnisse"></c:set>
		<c:set var="EmptyMessage" value="Es wurden keine Dokumente gefunden."></c:set>
	</c:otherwise>
	

</c:choose>

<!-- If this Page is used to show Search-Results the Buttons "Kursuebersicht", "Filter"
    and "Upload" are not shown on the page.
    NOTE: Not the best solution, since the surrounding divs are being cut out but 
    not their closing counterparts. (Still, seems to work).
     -->
<c:if test="${not empty kurs }">

<jsp:include page="commenIncludes.jsp"></jsp:include>
    <div class="container">
        <div class="jumbotron">
        
            <a href="courseOverview.jsp" class="btn btn-primary btn-lg" style="margin-bottom: 20px;"><span class="glyphicon glyphicon-hand-left"></span> Kurs&uuml;bersicht</a>
                
            <button type="button"  style="margin-bottom: 20px;" class="btn btn-primary btn-lg" data-toggle="collapse" data-target="#filter"><span class="glyphicon glyphicon-filter"></span> Filter</button>

            <button type="button" data-toggle="modal" data-target="#modalUpload" class="btn btn-primary btn-lg" style="margin-bottom: 20px;"><span class="glyphicon glyphicon-upload"></span> Upload</button>
            
            <div id="filter" class="container collapse" style="padding:0px;">
                   <u:getFilter kurs="${kurs.kursId}"></u:getFilter>
            </div>
</c:if>
           <br>
            <div class="panel panel-primary">
                <div class="panel-heading">${Panelheader}</div>
                <div class="panel-body">
                    <c:choose>
                        <c:when test="${not empty dokList }">
                        
                            <div class="list-group">
                            <!--  Loop that iterates over every single document stored in the "dokList" Attribute.
                                  There are three variables at the beginning that are being set for each document
                                  to simplify their Access.  -->
                                <c:forEach items="${dokList}" varStatus="loop">
                                
                                <c:set var="DokID" value="${dokList[loop.index].dokId}"></c:set>
                                <c:set var="DokName" value="${dokList[loop.index].name}"></c:set>
                                <c:set var="DokVisible" value="${dokList[loop.index].sichtbar}"></c:set>
                                
                                <!--  The Document is only being displayed completely if its visibility attribute is set to visible.-->
                                
                                <!-- Display the download button, the info popover and the delete Button (if the user is 
                                        an Admin.)  -->
                                <c:choose>
                                    <c:when test="${DokVisible == true }">
                                        <li class="list-group-item">
                                        
                                            <p:DocumentPopover dok="${dokList[loop.index]}"></p:DocumentPopover>
                                            <span>${DokName}</span>
                                            <d:DocumentButton type="download" id="${DokID}"></d:DocumentButton>
                                            
                                            <c:if test="${Admin == true}">
                                                <d:DocumentButton type="delete" id="${DokID}"></d:DocumentButton>
                                            </c:if>
                                            
                                        </li>
                                        
                                    </c:when>
                                    <c:otherwise>
                                        <div class="alert alert-info"><b>${DokName}</b>  muss noch freigegeben werden.</div>
                                    </c:otherwise>
                                </c:choose>
                                
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>

                            <div class="alert alert-info">${EmptyMessage}</div>

                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript" src="js/loadPopover.js"></script>

    <c:if test="${logedInPerson.getClass().name == 'de.dbae.administration.Student' && logedInPerson.premium == false }">
            <jsp:include page="advertisment.jsp"></jsp:include>
    </c:if>
        
</body>
</html>