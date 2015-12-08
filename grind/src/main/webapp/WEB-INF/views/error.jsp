<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<!--<meta http-equiv="refresh" content="5; <c:url value="/login" />"> -->
<title>Yhteysvirhe</title>
</head>
<body style="font: normal 18px 'Trebuchet MS';">
<div style="border: 0px dashed #000000; text-align: center; overflow: hidden; position: absolute; width: 500px; height: 600px; top: 50%; left: 50%; margin-top: -300px; margin-left: -250px;">
<p>Sovellus meni rikki.</p>
<p><img src="<c:url value="/resources/img/grind-shattered.png" />" /></p>
<p>Hae kahvia ja yritä hetken kuluttua uudelleen.</p>
<p>Olemme erittäin pahoillamme!</p>
<a href="<c:url value="/login" />">Sisäänkirjautuminen</a>
<!--<p>Siirrytään sisäänkirjaussivulle 5 sekunnin kuluttua...<p>-->
<!--
    Failed URL: ${url}
    Exception:  ${exception}
    Exception message: {exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
    </c:forEach>
    -->
</div>
</body>
</html>