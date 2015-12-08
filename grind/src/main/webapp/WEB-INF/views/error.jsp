<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
</div>
</body>
</html>