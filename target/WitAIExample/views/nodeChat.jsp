
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js">
</script>
<script type="text/javscript">
	alert("hello");
</script>
</head>
<body>

	<p>Start typing message in below texarea:</p>
	User Says:
	<br />

	<textarea rows="5" cols="50" id="AssetId"></textarea>
	<br />
	<input type="button" value="Send Message"
		onclick="javascript:getContent()" />
	<p>
		Bot Reply: <span></span>
	</p>
	<textarea rows="10" cols="50" id="resp" disabled="true"></textarea>

</body>
</html>

