<%-- 
  - Author(s): Jonathan Lochmann, Maxim Shulyatev, Julius Bartels
  - Date:
  - Copyright Notice:
  - @(#)
  - Description: 
  --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

</style>
</head>
<body>

	<div id="dokumenteFreigeben" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">

			<div class="modal-content" style="padding: 10px;">
				<div class="modal-header">
					<h4 class="modal-title">Dokument Freigeben</h4>
				</div>
				<div class="modal-body">
					<div id="resultOfDok">
						
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="js/loadAjaxToModal.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#dokumenteFreigeben").on('shown.bs.modal', function(e) {
		initAjaxLoadingProcess("DokumenteFreigeben", "#dokumenteFreigeben", "#resultOfDok", "Es gibt keine freizugebende Dokumente.");
	});
});
</script>


</body>
</html>