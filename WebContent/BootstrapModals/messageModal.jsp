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

	<c:if test="${ not empty message }">
		<div id="warningModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content" style="background-color: ${message.color};">
					<div class="modal-body">
						<div class="alert alert-${message.alert}" style="border: none;">
							<strong>${message.message}</strong>
						</div>
						<button type="button" class="btn btn-${message.alert}" data-dismiss="modal">OK</button>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(window).on('load', function() {
				$('#warningModal').modal('show');
			});
		</script>
		<c:remove var="message" />
	</c:if>
</body>
</html>