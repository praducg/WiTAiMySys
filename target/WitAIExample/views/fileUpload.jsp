<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome User</title>
<script type="text/javascript">
	
	//JSON.String
	function validateForm() {
		var x = document.getElementById("chat").value;
		if (x == null || x == "") {
			alert("Please enter some text and click on Send");
			returnfalse;
		}
	}
	function validate_fileupload(fileName) {
		var allowed_extensions = new Array("jpg", "png", "gif");
		if (fileName.value == '') {
			alert('Please upload the attachment');
			return false;
		}

		var file_extension = fileName.split('.').pop(); // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.
		for (var i = 0; i <= allowed_extensions.length; i++) {
			if (allowed_extensions[i] == file_extension) {
				return true; // valid file extension
			}
		}

		return false;
	}
</script>
</head>
<body>
	<h3>File Upload:</h3>
	Select a file to upload:
	<br />
	<form action="/WitAIExample/chat/fileUpload" method="post"
		enctype="multipart/form-data">
		<input type="file" name="fileName" id="file" size="50"
			onchange="validate_fileupload(this.value);" /> <br /> <input
			type="submit" value="Upload Jpeg file" />
	</form>
</body>
</html>