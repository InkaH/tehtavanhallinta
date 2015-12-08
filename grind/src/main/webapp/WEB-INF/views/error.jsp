<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<style>
p {text-align:center;}
.centered {
font: normal 18px 'Trebuchet MS';
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);	
}
</style>
<title>Yhteysvirhe</title>
</head>
<body>
<div class="centered">
<p>Nyt sinä sen rikoit!</p>
<img src="<c:url value="/resources/img/grind-shattered.png" />"/>
<p>Hae kahvia ja yritä hetken kuluttua uudelleen.</p>
<p>Olemme erittäin pahoillamme!</p>
<p>${exception}</p>
</div>
</body>
</html>