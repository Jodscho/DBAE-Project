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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>


	<div id="modalAuthentication" class="modal fade" role="dialog" data-backdrop="static" >
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Authentication</h4>
				</div>
				<div class="modal-body">
				<p>Please enter the Key that was send to your email adress:</p>
					<form action="RegistrationServlet" method="get">
						<div class="form-group">
							<label for="email">Key:</label> <input type="text"
								class="form-control" name="key">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">OK</button>
						</div>
					</form>
				</div>
			</div>


		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
    $(window).on('load',function(){
        $('#modalAuthentication').modal('show');
    });
</script>

</body>
</html>