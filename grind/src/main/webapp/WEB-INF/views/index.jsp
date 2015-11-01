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
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value="/resources/img/favicon.ico" />" rel="shortcut icon" type="image/x-icon" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<c:url value="/resources/js/script.js" /> "></script>
</head>
<body>
<div id="container">
<h1>G R I N D</h1>
<div id="add" style="height: ${muokkaus=='0' ? '35px' : 'auto'}; color: ${muokkaus=='0' ? '#000000' : '#FF0000'};">
<div id="addtaskform">
<c:if test="${muokkaus=='0'}">
<legend style="color: #000000; font-size: 18px;">+ Lisää tehtävä</legend>
</c:if>
<c:if test="${muokkaus=='1'}">
<legend style="color: #FF0000; font-size: 18px;">Muokkaa tehtävää</legend>
</c:if>
<form:form class="form-inline" modelAttribute="uusiTehtava" action="add" method="post" accept-charset="UTF-8">
<div class="form-group">
<!-- Piilokentat -->
<form:hidden path="id" value="0" />
<form:hidden path="status" value="0" />
<p>
Tehtävä ja lisätiedot:<br>
<form:input path="kuvaus" cssClass="form-control inline" placeholder="Aihe" /> 
<form:input path="tiedot" cssClass="form-control inline" placeholder="Kuvaus" /> 
</p>
<p>
Ajankohta:<br>
<form:input path="ajankohtaPvm" cssClass="form-control inline" placeholder="Ajankohta pvm" />
<form:input path="ajankohtaKlo" cssClass="form-control inline" placeholder="Ajankohta klo" />
</p>
<p>
Muistutus:<br>
<form:input path="muistutusPvm" cssClass="form-control inline" placeholder="Muistutus pvm" />
<form:input path="muistutusKlo" cssClass="form-control inline" placeholder="Muistutus klo" />
<br><br>
<button type="submit" class="btn btn-default"><c:out value="${muokkaus=='0' ? 'Lisää' : 'Tallenna'}" /></button>
</p>
</div>
</form:form>
</div>
</div>

<c:if test="${empty tehtavat}">
Ei tehtäviä tietokannassa.
</c:if>
<c:if test="${not empty tehtavat}">
<c:forEach var="t" items="${tehtavat}">
<div class="task" style="font: normal 16px 'Trebuchet MS'; margin: 3px;">
<div class="delete">
<form action="act" method="post">
<input type="hidden" id="delItem" name="delItem" value="0" />
<input type="hidden" id="editing" name="editing" value="0" />
<div class="dropdown">
<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="true"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true" style="margin: 8px 8px 0 0; color: #696969;"></span></a>
<ul class="dropdown-menu dropdown-menu-right">
<li>
	<a id="editLink" href="#" onclick="
		document.forms[1].editing.value='1';
		document.forms[1].delItem.value='${t.id}';
		document.forms[1].submit();
		">Muokkaa</a>
</li>
<li><a class="dd-select" href="#">Jaa...</a></li>
<li role="separator" class="divider"></li>
<li>
	<a href="#" onclick="
		document.forms[1].editing.value='0';
		document.forms[1].delItem.value='${t.id}';
		if(!confirm('Haluatko poistaa tehtävän pysyvästi?')){
			return false;
		} else {
			document.forms[1].submit();
		}">Poista</a>
</li>
</ul>
</div>
</form>
</div>
<c:out value="${t.kuvaus}" />:&nbsp;<c:out value="${t.tiedot}" /><br>
<!-- [d.M.yyyy] [HH:mm] -->
<fmt:parseDate value="${t.ajankohtaPvm}" pattern="yyyy-MM-dd" var="parsedAjankohtaPvm" type="date" />
<fmt:formatDate value="${parsedAjankohtaPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:parseDate value="${t.ajankohtaKlo}" pattern="HH:mm" var="parsedAjankohtaKlo" type="date" />
<fmt:formatDate value="${parsedAjankohtaKlo}" pattern="HH:mm" type="date" />&nbsp;
<span style="color: #666699; font-size: 12px;"><br>Muistutus:&nbsp;
<fmt:parseDate value="${t.muistutusPvm}" pattern="yyyy-MM-dd" var="parsedMuistutusPvm" type="date" />
<fmt:formatDate value="${parsedMuistutusPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:parseDate value="${t.muistutusKlo}" pattern="HH:mm" var="parsedMuistutusKlo" type="date" />
<fmt:formatDate value="${parsedMuistutusKlo}" pattern="HH:mm" type="date" />
</span>
</div>
</c:forEach>
</c:if>
</div>

</body>
</html>