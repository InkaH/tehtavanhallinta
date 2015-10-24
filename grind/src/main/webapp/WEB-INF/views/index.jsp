<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G R I N D</title>
<link href="<c:url value="/resources/styles/tyyli.css" />" rel="stylesheet">
<link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" />" rel="stylesheet">
<script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" /> "></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" /> "></script>
<script src="<c:url value="/resources/js/javascript.js" /> "></script>
</head>
<body>
<div class="container">
<div class="header">
<h1>G R I N D</h1>
</div>
<div class="tasks">
<c:if test="${empty tehtavat}">
Ei tehtäviä tietokannassa.
</c:if>
<c:if test="${not empty tehtavat}">
<c:forEach var="t" items="${tehtavat}">
<div class="task">
<c:out value="${t.kuvaus}" />:&nbsp;<c:out value="${t.tiedot}" /><br>
<!-- [d.M.yyyy] [HH:mm] -->
<fmt:parseDate value="${t.ajankohtaPvm}" pattern="yyyy-MM-dd" var="parsedAjankohtaPvm" type="date" />
<fmt:formatDate value="${parsedAjankohtaPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:parseDate value="${t.ajankohtaKlo}" pattern="HH:mm" var="parsedAjankohtaKlo" type="date" />
<fmt:formatDate value="${parsedAjankohtaKlo}" pattern="HH:mm" type="date" />&nbsp;
<span class="muistutus">(&nbsp;Muistutus:&nbsp;
<fmt:parseDate value="${t.muistutusPvm}" pattern="yyyy-MM-dd" var="parsedMuistutusPvm" type="date" />
<fmt:formatDate value="${parsedMuistutusPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:parseDate value="${t.muistutusKlo}" pattern="HH:mm" var="parsedMuistutusKlo" type="date" />
<fmt:formatDate value="${parsedMuistutusKlo}" pattern="HH:mm" type="date" />
)</span>
</div>
</c:forEach>
</c:if>
</div>	
<button type="button" class="btn btn-default btn-lg btn-xs" id="addtaskbtn">
<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
</button>
<div id=addtaskform hidden="">
<form:form class="form-inline" modelAttribute="uusiTehtava" method="post" accept-charset="UTF-8">
<div class="form-group">
<!-- Piilokentat -->
<form:hidden path="id" value="0" />
<form:hidden path="status" value="0" />
Tehtävä ja lisätiedot:<br>
<form:input path="kuvaus" cssClass="form-control inline" placeholder="Aihe" /> 
<form:input path="tiedot" cssClass="form-control inline" placeholder="Kuvaus" /> 
<br>Ajankohta:<br>
<form:input path="ajankohtaPvm" cssClass="form-control inline" placeholder="Ajankohta pvm" />
<form:input path="ajankohtaKlo" cssClass="form-control inline" placeholder="Ajankohta klo" />
<br>Muistutus:<br>
<form:input path="muistutusPvm" cssClass="form-control inline" placeholder="Muistutus pvm" />
<form:input path="muistutusKlo" cssClass="form-control inline" placeholder="Muistutus klo" />
<br><br>
<button type="submit" class="btn btn-default">Lisää</button>
</div>
</form:form>
</div>
</div>	
</body>
</html>