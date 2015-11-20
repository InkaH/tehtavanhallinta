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
<script type="application/javascript" charset="UTF-8" src="<c:url value="/resources/js/script.js" />"></script>

<!-- jqueryUI datepicker stuff -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="<c:url value="/resources/js/datepickerui-fi.js" />"></script>
</head>

<body>
<!-- bootstrap container with large margins (all content) -->
<div class="container">

<!-- 2nd main row: task creation and editing form -->
<!-- If editing is not active (edit==0) font color is black, otherwise red -->
<div class="row" style="color: ${edit=='0' ? '#000000' : '#ff0066'};">

<!-- if editing is not active, show header '+ Luo uusi tehtava' -->
<c:if test="${edit=='0'}">

<!-- 1st optional inner row -->
<div class="row">
<div class="col-sm-offset-2 col-sm-10">
<h3><span data-toggle="collapse" data-target="#add" style="cursor: pointer;">&nbsp;&nbsp;+ Luo uusi tehtävä</span></h3>
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

<div class="row">
<div class="form-group">
<!-- bootstrap class control-label sets the label before input field (to the left) with alignment right -->
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">* Tehtävä:</label>
<div class="col-sm-6">
<!-- spring form: cssClass = class (html) -->
<form:input path="kuvaus" cssClass="form-control" placeholder="Kirjoita tehtävän aihe" required="required" maxlength="80" />
</div>
<div class="col-sm-3">
</div>
</div>
</div>

<div class="row">
<div class="form-group">
<label class="control-label col-sm-offset-1 col-sm-2" for="ryhma">Ryhmätunnus:</label>
<div class="col-sm-6">
<form:input path="ryhma" cssClass="form-control" style="text-transform: uppercase" maxlength="50" />
</div>
<div class="col-sm-3">
</div>
</div>
</div>

<div class="row">
<div class="form-group">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Lisätiedot:</label>
<div class="col-sm-6">
<!-- bootstrap class form-control makes the element full width of parent element in a form -->
<form:textarea path="tiedot" cssClass="form-control" rows="5" placeholder="Kirjoita lisätiedot" maxlength="500" /> 
</div>
<div class="col-sm-3">
</div>
</div>
</div>

<!-- bootstrap class has-feedback puts glyphicon icon inside the input field -->
<div class="row">
<div class="form-group has-feedback">
<label class="control-label col-sm-offset-1 col-sm-2" for="kuvaus">Ajankohta:</label>
<div class="controls bootstrap-timepicker col-sm-3" >
<form:input path="ajankohtaPvm" cssClass="form-control text-center datetime" id="datepicker"/>
<i class="form-control-feedback glyphicon glyphicon-calendar"></i>
</div>
<div class="col-sm-3">
<form:input path="ajankohtaKlo" cssClass="form-control text-center" id="timepicker"/>
<!-- bootstrap form-control-feedback puts glyphicon icon to the right end of the input field (not after text content) -->
<i class="form-control-feedback glyphicon glyphicon-time"></i>
</div>
<div class="col-sm-3">
</div>
</div>
</div>

<div class="row">
<div class="form-group"> 
<div class="col-sm-offset-3 col-sm-3">
<button type="submit" class="btn btn-default form-control"><span class="glyphicon glyphicon-download-alt"></span>&nbsp;&nbsp;TALLENNA</button>
</div>
<div class="col-sm-3">
<button onclick="document.forms[4].submit(); return false;" class="btn btn-default form-control"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;PERUUTA</button>
</div>
<div class="col-sm-3"></div>
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

<!-- create current date-time to compare if the task is expired -->
<jsp:useBean id="now" class="java.util.Date" />

<!-- task content boxes generated in loop -->
<c:forEach var="t" items="${tehtavat}">

<fmt:parseDate value="${t.ajankohtaPvm} ${t.ajankohtaKlo}" pattern="yyyy-MM-dd HH:mm" var="parsedAjankohta" type="date" />
<fmt:parseDate value="${t.ajankohtaPvm}" var="compTaskDate" pattern="yyyy-MM-dd" />
<fmt:parseDate value="1970-01-01" var="compIdentifier" pattern="yyyy-MM-dd" />

<div class="row">
<!-- bootstrap class well is a simple styled content box. 2 cols empty, 8 cols for well, 2 cols empty (total 12) -->
<div class="col-sm-offset-2 col-sm-8 well ${(parsedAjankohta > now) ? 'mark-task' : ((compTaskDate == compIdentifier) ? 'mark-note' : 'mark-warn')}">

<!-- dropdown list of optional functions of a single task -->
<div class="task-options">
<div class="dropdown">
<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="true"><span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true" style="margin: 8px 8px 0 0; color: #FFFFFF;"></span></a>
<ul class="dropdown-menu dropdown-menu-right">
<li>
<!-- edit option: on click change value of editTask attribute in edit form and submit the form -->
<a href="#" onclick="document.forms[2].editTask.value='${t.id}';document.forms[2].submit();"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Muokkaa</a>
</li>
<li>
<a href="#" onclick="sh=prompt('Anna ryhmätunnus:','${t.ryhma}');if(sh!=null){document.forms[3].shareTask.value='${t.id}';document.forms[3].groupID.value=sh;document.forms[3].submit();}"><span class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;Jaa...</a>
</li>
<li role="separator" class="divider"></li>
<li>

<!-- remove option: on click change value of delTask attribute in delete form and submit the form -->
<a href="#" onclick="if(!confirm('Haluatko poistaa tehtävän pysyvästi?')){return false;}else{document.forms[1].delTask.value='${t.id}';document.forms[1].submit();}"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Poista</a>
</li>
</ul>
</div>
</div>

<!-- bootstrap <strong> used for the header of task -->
<span><c:out value="${t.kuvaus}" /></span>

<c:if test="${not empty t.ryhma}">
<span class="groupid"><small><c:out value="${t.ryhma}" /></small></span>
</c:if>

<c:if test="${not empty t.tiedot}">
<span class="tiedot">&nbsp;&#8811;&nbsp;&nbsp;</span><span hidden="hidden"><c:out value="${t.tiedot}" /></span>
</c:if>
<br>
<!-- jstl: time objects parsed from localdate/localtime to date objects -->
<span>
<small>
<c:out value="${(parsedAjankohta > now) ? '' : (compTaskDate == compIdentifier ? '' : 'Erääntynyt: ')}" />
<c:if test="${compTaskDate != compIdentifier}">
<fmt:parseDate value="${t.ajankohtaPvm}" pattern="yyyy-MM-dd" var="parsedAjankohtaPvm" type="date" />
<fmt:parseDate value="${t.ajankohtaKlo}" pattern="HH:mm" var="parsedAjankohtaKlo" type="time" />
<fmt:formatDate value="${parsedAjankohtaPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
<fmt:formatDate value="${parsedAjankohtaKlo}" pattern="HH:mm" type="time" />
</c:if>
</small>
</span>
</div>
<div class="col-sm-2"></div>
</div>

<!-- FORM[1]: DELETE -->
<form id="delForm" action="del" method="post">
<input type="hidden" id="delTask" name="delTask" value="0" />
</form>
<!-- FORM[2]: EDIT -->
<form id="editForm" action="edit" method="post">
<input type="hidden" id="editTask" name="editTask" value="0" />
</form>
<!-- FORM[3]: SHARE -->
<form id="shareForm" action="share" method="post">
<input type="hidden" id="shareTask" name="shareTask" value="0" />
<input type="hidden" id="groupID" name="groupID" value="0" />
</form>
<!-- FORM[4]: CANCEL -->
<form id="cancelForm" action="cancel" method="post">
</form>
</c:forEach>
</c:if>
</div>
<!-- Banner -->
<div id="banner">
<div class="row">
<div class="col-sm-offset-2 col-sm-10">
<h1 style="color: #FFFFFF;"><img style="width: 50px; margin: -5px 5px 0 15px;" src="<c:url value="/resources/img/grind-logo-teksti.png" />" /> G R I N D</h1>
</div>
</div>
</div>

</body>
</html>