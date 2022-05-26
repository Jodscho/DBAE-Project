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
</head>
<body>


	<div id="contactAdmin" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content" style="padding: 10px;">
				<div class="modal-header">
					<h4 class="modal-title">Admin Kontaktieren</h4>
				</div>
				<div class="modal-body">
					<form action="ContactAdmin" method="get">
						<div class="form-group">
							<label>Betreff:</label> <input type="text"
								class="form-control"  name="subject">
						</div>
						<div class="form-group">
							<label>Nachricht:</label>
							<textarea class="form-control" rows="5" name="messageArea" style="resize:none;"></textarea>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Abschicken</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>