<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>


<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript">
	function getContent() {
		var fieldData = {
			"object" : "msg",
			"entry" : [ {
				"messaging" : [ {
					"message" : {
						"text" : $("#AssetId").val()
					}
				} ]
			} ]
		}

		$.ajax({
			//async : true,
			//crossDomain : true,
			type : "POST",
			url : "http://localhost:8080/WitAIExample/chat/greetings",
			
			//processData : false,
			data : fieldData,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});

	}
</script>
</head>
<body>
	<form action=""></form>
	<p>Start typing message in below texarea:</p>
	User Says:
	<br />

	<textarea rows="5" cols="50" id="AssetId"></textarea>
	<br />
	<input type="button" value="Send Message" onclick="getContent()" />
	<p>
		Bot Reply: <span></span>
	</p>
	<textarea rows="10" cols="50" id="resp" disabled="true"></textarea>

</body>
</html>

