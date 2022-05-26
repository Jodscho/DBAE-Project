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

	<div id="upgradeAccountModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<div class="modal-content" style="padding: 10px;">
				<div class="modal-header">
					<h4 class="modal-title"><span class="glyphicon glyphicon-star-empty"></span> Upgrade to Premium <span class="glyphicon glyphicon-star-empty"></span></h4>
				</div>
				<div class="modal-body">
					<form action="UpgradeAccount" method="post">
						<div class="form-group">
							<p style="font-size:14px;"><i>Die IBAN wird zusammen mit dem Account vermerkt. Sobald der entsprechende Betrag auf dem <a href="#" data-toggle="popover" data-html="true" title="Konto" data-content="<b>Empfänger:</b> Max Mustermann<br><b>BIC:</b> ABCDEF12ABC<br><b>IBAN:</b> DE12345917060021442500<br>Beispielbank">Konto</a> eingegangen ist, wird der Premium-Zugang von einem Admin freigeschaltet.</i></p>
						
							<label>Gebuehr: 10 &euro;</label>
						</div>

						<div class="form-group">
							<div class="form-group">

								<label for="pwd">IBAN:</label> <input type="text"
									class="form-control" name="iban" required>
							</div>
							<button type="submit" class="btn btn-primary">Fertig</button>
						</div>
					</form>
				</div>
			</div>


		</div>
	</div>
	
	
<script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});
</script>
	

</body>
</html>