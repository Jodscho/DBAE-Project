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
input[type="file"] {
	display: none;
}

.customFileUpload {
	border: 1px solid #ccc;
	display: inline-block;
	padding: 6px 12px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div id="modalUpload" class="modal fade" role="dialog">
		<div class="modal-dialog  modal-sm">

			<div class="modal-content" style="padding: 10px;">
				<div class="modal-header">
					<h4 class="modal-title">Upload File</h4>
				</div>
				<div class="modal-body">
					<form action="UploadServlet" method="post"
						enctype="multipart/form-data">
						<div class="form-group">
							<label for="fileUpload" class="customFileUpload"><span
								class="glyphicon glyphicon-upload"></span> Choose File<input
								type="file" id="fileUpload" name="uploadFile" /></label><br><span
								id="displayFile"></span><br>
						</div>
						<div class="form-group">
							<label for="beschreibung">Kurzbeschreibung (optional):</label>
							<textarea class="form-control" id="beschreibung" style="resize:none;"
								name="beschreibung" rows="2"></textarea>
						</div>
						<div class="form-group">
						<label>Semester: </label><br>
						<select class="btn btn-default" id="semester" name="semester">
							<option selected value="WiSe 14/15">WiSe 14/15</option>
							<option value="SoSe 15">SoSe 15</option>
							<option value="WiSe 15/16">WiSe 15/16</option>
							<option value="SoSe 16">SoSe 16</option>
						</select>
						</div>
						<div class="form-group">
						<label>File Type: </label>
						<div class="radio">
							<label><input type="radio" name="filetype"
								value="PROBEKLAUSUR">Probeklausur </label>
						</div>
						<div class="radio">
							<label><input type="radio" name="filetype"
								value="TUTORIUM">Tutorium </label>
						</div>
						<div class="radio">
							<label><input type="radio" name="filetype" value="UEBUNG">Übung
							</label>
						</div>
						<div class="radio">
							<label><input type="radio" name="filetype"
								value="HAUSAUFGABE">Hausaufgabe </label>
						</div>
						<div class="radio">
							<label><input type="radio" name="filetype"
								value="LERNZETTEL">Lernzettel </label>
						</div>
						<div class="radio">
							<label><input type="radio" name="filetype"
								value="WEITERES">Weiteres </label>
						</div>
</div>

						<div class="form-group" style="display: none;"> 
							<input type="text" class="form-control" name="kursId"
								value="${kurs.kursId}">
						</div>
						<button type="submit" class="btn btn-primary courseLink">Upload</button>
					</form>

				</div>
			</div>


		</div>
	</div>
    
    <script	src="js/loadingoverlay.min.js"></script>
    <script type="text/javascript">
	$('input[type=file]').change(function(e) {
		$("#displayFile").html($('input[type=file]').val());
	});
    </script>
    

</body>
</html>