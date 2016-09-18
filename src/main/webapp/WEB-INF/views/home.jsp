<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>

<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />
<script src="<c:url value="/resources/js/jQuery.js" />"></script>
<script>
$(document).ready(function(){
    $("h1").click(function(){
        $("h1").fadeToggle(300);
    });
});
</script>

</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
