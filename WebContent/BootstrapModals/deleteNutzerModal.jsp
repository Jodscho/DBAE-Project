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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div id="deleteNutzer" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<div class="modal-content" style="padding: 10px;">
				<div class="modal-header">
					<h4 class="modal-title">Nutzer Löschen</h4>
				</div>
				<div class="modal-body">
					<div id="resultOfS">
						
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="js/loadAjaxToModal.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#deleteNutzer").on('shown.bs.modal', function(e) {
		initAjaxLoadingProcess("DeleteNutzer", "#deleteNutzer", "#resultOfS", "Es gibt keine Studenten.");
	});
});
</script>

</body>
</html>