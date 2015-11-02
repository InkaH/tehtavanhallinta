<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="fi">
<head>

<title>G R I N D</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link href="<c:url value="/resources/styles/tyyli.css" />" rel="stylesheet">
<link href="<c:url value="/resources/img/favicon.ico" />" rel="shortcut icon" type="image/x-icon" />
<script src="<c:url value="/resources/js/script.js" />"></script>

</head>

<body>
<div class="container">
<div class="row">
<div class="col-sm-offset-1 col-sm-11">
<h1>G R I N D</h1>
</div>
</div>
<div class="row" style="color: ${muokkaus=='0' ? '#000000' : '#FF0000'};">
<c:if test="${muokkaus=='0'}">
<div class="row">
<div class="col-sm-offset-2 col-sm-10">
<h3><span data-toggle="collapse" data-target="#add" style="cursor: pointer;"> + Luo uusi tehtävä</span></h3>
</div>
</div>
</c:if>
<c:if test="${muokkaus=='1'}">
<div class="row">
<div class="col-sm-offset-2 col-sm-10">
<h3>Muokkaa tehtävää</h3>
</div>
</div>
</c:if>
<div id="add" class="${muokkaus=='0' ? 'collapse' : 'collapse in'}">
<form:form role="form" class="form-horizontal" modelAttribute="uusiTehtava" action="add" method="post" accept-charset="UTF-8">

<form:hidden path="id" value="0" />
<form:hidden path="status" value="0" />

<div class="form-group">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Tehtävä:</label>
<div class="col-sm-6">
<form:input path="kuvaus" cssClass="form-control" placeholder="Kirjoita tehtävä" />
</div>
<div class="col-sm-3">
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Lisätiedot:</label>
<div class="col-sm-6">
<form:textarea path="tiedot" cssClass="form-control" rows="5" placeholder="Kirjoita lisätiedot" /> 
</div>
<div class="col-sm-3">
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Ajankohta:</label>
<div class="controls bootstrap-timepicker col-sm-3">
<form:input path="ajankohtaPvm" cssClass="form-control text-center datetime" />
<i class="icon-time"></i>
</div>
<div class="col-sm-3">
<form:input path="ajankohtaKlo" cssClass="form-control text-center" /> 
</div>
<div class="col-sm-3">
</div>
</div>

<div class="form-group">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Muistutus:</label>
<div class="col-sm-3">
<form:input path="muistutusPvm" cssClass="form-control text-center" /> 
</div>
<div class="col-sm-3">
<form:input path="muistutusKlo" cssClass="form-control text-center" /> 
</div>
<div class="col-sm-3">
</div>
</div>

<div class="form-group"> 
<div class="col-sm-offset-3 col-sm-6">
<button type="submit" class="btn btn-default form-control"><span class="glyphicon glyphicon-download-alt"></span>&nbsp;TALLENNA</button>
</div>
</div>
</form:form>
</div>
</div>

<c:if test="${empty tehtavat}">
Ei tehtäviä tietokannassa.
</c:if>
<c:if test="${not empty tehtavat}">
<c:forEach var="t" items="${tehtavat}">
<div class="row">
<div class="col-sm-offset-2 col-sm-8 well">
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
		"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Muokkaa</a>
</li>
<li><a class="dd-select" href="#"><span class="glyphicon glyphicon-share-alt"></span>&nbsp;Jaa...</a></li>
<li role="separator" class="divider"></li>
<li>
	<a href="#" onclick="
		document.forms[1].editing.value='0';
		document.forms[1].delItem.value='${t.id}';
		if(!confirm('Haluatko poistaa tehtävän pysyvästi?')){
			return false;
		} else {
			document.forms[1].submit();
		}"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Poista</a>
</li>
</ul>
</div>
</form>
</div>
<strong><c:out value="${t.kuvaus}" /></strong><br><c:out value="${t.tiedot}" /><br>
<!-- [d.M.yyyy] [HH:mm] -->
<fmt:parseDate value="${t.ajankohtaPvm}" pattern="yyyy-MM-dd" var="parsedAjankohtaPvm" type="date" />
<fmt:formatDate value="${parsedAjankohtaPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:parseDate value="${t.ajankohtaKlo}" pattern="HH:mm" var="parsedAjankohtaKlo" type="date" />
<fmt:formatDate value="${parsedAjankohtaKlo}" pattern="HH:mm" type="date" />
<!--  
<br>
<small>Muistutus:&nbsp;
<fmt:parseDate value="${t.muistutusPvm}" pattern="yyyy-MM-dd" var="parsedMuistutusPvm" type="date" />
<fmt:formatDate value="${parsedMuistutusPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:parseDate value="${t.muistutusKlo}" pattern="HH:mm" var="parsedMuistutusKlo" type="date" />
<fmt:formatDate value="${parsedMuistutusKlo}" pattern="HH:mm" type="date" />
</small>
-->
</div>
</div>
</c:forEach>
</c:if>
</div>

</body>
</html>