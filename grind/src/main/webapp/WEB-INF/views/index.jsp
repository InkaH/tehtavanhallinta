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
<div class="task">
<c:if test="${empty tehtavat}">
Ei tehtäviä tietokannassa.
</c:if>
<c:if test="${not empty tehtavat}">
<c:forEach var="t" items="${tehtavat}">
<div>
<c:out value="${t.kuvaus}" />:&nbsp;<c:out value="${t.tiedot}" /><br>
<fmt:formatDate pattern="d.M.yyyy HH:mm" value="${t.ajankohta}" /><br>
<fmt:formatDate pattern="d.M.yyyy HH:mm" value="${t.muistutus}" />
</div>
</c:forEach>
</c:if>	
</div>
<div class="edit">
<button type="button" class="btn btn-default btn-lg editbtn btn-xs">
<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
</button>
</div>
</div>	
<button type="button" class="btn btn-default btn-lg btn-xs" id="addtaskbtn">
<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
</button>
<div id=addtaskform hidden="">
<form:form class="form-inline" modelAttribute="uusiTehtava" method="post" accept-charset="UTF-8">
<div class="form-group">
<form:hidden path="id" value="0" />
<form:input path="kuvaus" cssClass="form-control inline" placeholder="Kirjoita tähän seuraava tehtävä!" /> 
<form:input path="tiedot" cssClass="form-control inline" placeholder="Kirjoita tähän lisätiedot" /> 
<form:hidden path="status" value="0" />
<fmt:formatDate value="${uusiTehtava.ajankohta}" var="aika" pattern="d.M.yyyy HH:mm" />
<form:input path="ajankohta" cssClass="form-control inline" value="${aika}" placeholder="Ajankohta [d.M.yyyy HH:mm]" />
<fmt:formatDate value="${uusiTehtava.muistutus}" var="muistutus" pattern="d.M.yyyy HH:mm" />
<form:input path="muistutus" cssClass="form-control inline" value="${muistutus}" placeholder="Muistutus [d.M.yyyy HH:mm]" />
<button type="submit" class="btn btn-default">Lisää</button>
</div>
</form:form>
</div>
</div>	
</body>
</html>