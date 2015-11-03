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

<!-- view configuration for mobile devices -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- bootstrap libraries (jquery and css) -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- application css stylesheet, favicon icon and scripts -->
<link href="<c:url value="/resources/styles/tyyli.css" />" rel="stylesheet">
<link href="<c:url value="/resources/img/favicon.ico" />" rel="shortcut icon" type="image/x-icon" />
<script src="<c:url value="/resources/js/script.js" />"></script>
</head>

<body>
<!-- bootstrap container with large margins (all content) -->
<div class="container">

<!-- 1st main row: header -->
<div class="row">

<!-- the responsive view is horizontally divided into 12 columns -->
<!-- first col empty, following 11 cols for header -->
<div class="col-sm-offset-1 col-sm-11">
<h1>G R I N D</h1>
</div>
</div>

<!-- 2nd main row: task creation and editing form -->
<!-- If editing is not active (edit==0) font color is black, otherwise red -->
<div class="row" style="color: ${edit=='0' ? '#000000' : '#FF0000'};">

<!-- if editing is not active, show header '+ Luo uusi tehtava' -->
<c:if test="${edit=='0'}">

<!-- 1st optional inner row -->
<div class="row">
<div class="col-sm-offset-2 col-sm-10">
<h3><span data-toggle="collapse" data-target="#add" style="cursor: pointer;"> + Luo uusi tehtävä</span></h3>
</div>
</div>
</c:if>

<!-- if editing is active (edit==1), show header 'Muokkaa tehtavaa' -->
<c:if test="${edit=='1'}">

<!-- 1st optional inner row -->
<div class="row">
<div class="col-sm-offset-2 col-sm-10">
<h3>Muokkaa tehtävää</h3>
</div>
</div>
</c:if>

<!-- if editing is not active, hide form (collapse), otherwise show form (collapse in) -->
<div id="add" class="${edit=='0' ? 'collapse' : 'collapse in'}">

<!-- the form gets tehtava object (uusiTehtava) from controller and puts bean attributes (values) into form fields -->
<form:form role="form" class="form-horizontal" modelAttribute="uusiTehtava" action="add" method="post" accept-charset="UTF-8">

<!-- when the form is binded to a bean, every bean attribute (path) must be present in the form (hidden or shown) -->
<form:hidden path="id" />
<form:hidden path="status" />
<div class="form-group">

<!-- bootstrap class control-label sets the label before input field (to the left) with alignment right -->
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Tehtävä:</label>
<div class="col-sm-6">

<!-- spring form: cssClass = class (html) -->
<form:input path="kuvaus" cssClass="form-control" placeholder="Kirjoita tehtävä" />
</div>
<div class="col-sm-3">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Lisätiedot:</label>
<div class="col-sm-6">

<!-- bootstrap class form-control makes the element full width of parent element in a form -->
<form:textarea path="tiedot" cssClass="form-control" rows="5" placeholder="Kirjoita lisätiedot" /> 
</div>
<div class="col-sm-3">
</div>
</div>

<!-- bootstrap class has-feedback puts glyphicon icon inside the input field -->
<div class="form-group has-feedback">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Ajankohta:</label>
<div class="controls bootstrap-timepicker col-sm-3">
<form:input path="ajankohtaPvm" cssClass="form-control text-center datetime" />
<i class="form-control-feedback glyphicon glyphicon-calendar"></i>
</div>
<div class="col-sm-3">
<form:input path="ajankohtaKlo" cssClass="form-control text-center" />

<!-- bootstrap form-control-feedback puts glyphicon icon to the right end of the input field (not after text content) -->
<i class="form-control-feedback glyphicon glyphicon-time"></i>
</div>
<div class="col-sm-3">
</div>
</div>

<div class="form-group"> 
<div class="col-sm-offset-3 col-sm-6">
<button type="submit" class="btn btn-default form-control"><span class="glyphicon glyphicon-download-alt"></span>&nbsp;&nbsp;TALLENNA</button>
</div>
</div>
</form:form>
</div>
</div>

<!-- UI gets list of all tasks (tehtavat) from controller and prints them if not empty -->
<c:if test="${empty tehtavat}">

<!-- 3rd optional main row -->
<div class="row">
<div class="col-sm-offset-2 col-sm-10">
Sinulla ei ole tehtäviä
</div>
</div>
</c:if>

<!-- 3rd optional main row -->
<c:if test="${not empty tehtavat}">

<jsp:useBean id="now" class="java.util.Date" />

<!-- task content boxes generated in loop -->
<c:forEach var="t" items="${tehtavat}">

<fmt:parseDate value="${t.ajankohtaPvm} ${t.ajankohtaKlo}" pattern="yyyy-MM-dd HH:mm" var="parsedAjankohta" type="date" />

<div class="row">

<!-- bootstrap class well is a simple styled content box. 2 cols empty, 8 cols for well, 2 cols empty (total 12) -->
<div class="col-sm-offset-2 col-sm-8 alert ${(parsedAjankohta > now) ? 'alert-info' : 'alert-danger'}">

<!-- dropdown list of optional functions of a single task -->
<div class="task-options">
<div class="dropdown">
<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="true"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true" style="margin: 8px 8px 0 0; color: #696969;"></span></a>
<ul class="dropdown-menu dropdown-menu-right">
<li>

<!-- edit option: on click change value of editTask attribute in edit form and submit the form -->
<a href="#" onclick="document.forms[2].editTask.value='${t.id}';document.forms[2].submit();"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Muokkaa</a>
</li>
<li><a class="dd-select" href="#"><span class="glyphicon glyphicon-share-alt"></span>&nbsp;Jaa...</a></li>
<li role="separator" class="divider"></li>
<li>

<!-- remove option: on click change value of delTask attribute in delete form and submit the form -->
<a href="#" onclick="document.forms[1].delTask.value='${t.id}';document.forms[1].submit();"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Poista</a>
</li>
</ul>
</div>
</div>

<!-- bootstrap <strong> used for the header of task -->
<strong><c:out value="${t.kuvaus}" /></strong><br><c:out value="${t.tiedot}" /><br>

<!-- jstl: time objects parsed from localdate/localtime to date objects -->

<c:out value="${(parsedAjankohta > now) ? '' : 'Erääntynyt: '}" />
<fmt:parseDate value="${t.ajankohtaPvm}" pattern="yyyy-MM-dd" var="parsedAjankohtaPvm" type="date" />
<fmt:parseDate value="${t.ajankohtaKlo}" pattern="HH:mm" var="parsedAjankohtaKlo" type="time" />
<fmt:formatDate value="${parsedAjankohtaPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:formatDate value="${parsedAjankohtaKlo}" pattern="HH:mm" type="time" />

</div>
</div>

<!-- FORM[1]: DELETE -->
<form id="delForm" action="del" method="post">
<input type="hidden" id="delTask" name="delTask" value="0" />
</form>
<!-- FORM[2]: EDIT -->
<form id="editForm" action="edit" method="post">
<input type="hidden" id="editTask" name="editTask" value="0" />
</form>
</c:forEach>
</c:if>
</div>
</body>
</html>