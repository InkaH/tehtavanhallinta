<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<style>
p, img {text-align:center !important; font: normal 18px 'Trebuchet MS';}

</style>
<title>Yhteysvirhe</title>
</head>
<body>
<div>
<p>Nyt sinä sen rikoit!</p>
<img src="<c:url value="/resources/img/grind-shattered.png" />" style="text-align: center !important; display: inline-block; position:relative;" />
<p>Hae kahvia ja yritä hetken kuluttua uudelleen.</p>
<p>Olemme erittäin pahoillamme!</p>
<p>${exception}</p>
</div>
</body>
</html>