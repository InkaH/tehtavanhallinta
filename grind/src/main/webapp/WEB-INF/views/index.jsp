<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- create current date-time to compare if the task is expired -->
<jsp:useBean id="now" class="java.util.Date" />

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

<link href="<c:url value="/resources/styles/style-common.css" />" rel="stylesheet">

<c:choose>
<c:when test="${theme == 1}"><link href="<c:url value="/resources/styles/tyyli1.css" />" rel="stylesheet"></c:when>
<c:when test="${theme == 2}"><link href="<c:url value="/resources/styles/tyyli2.css" />" rel="stylesheet"></c:when>
<c:when test="${theme == 3}"><link href="<c:url value="/resources/styles/tyyli3.css" />" rel="stylesheet"></c:when>
<c:when test="${theme == 4}"><link href="<c:url value="/resources/styles/tyyli4.css" />" rel="stylesheet"></c:when>
<c:when test="${theme == 5}"><link href="<c:url value="/resources/styles/tyyli5.css" />" rel="stylesheet"></c:when>
<c:when test="${theme == 6}"><link href="<c:url value="/resources/styles/tyyli6.css" />" rel="stylesheet"></c:when>
<c:when test="${theme == 7}"><link href="<c:url value="/resources/styles/tyyli7.css" />" rel="stylesheet"></c:when>
<c:when test="${theme == 8}"><link href="<c:url value="/resources/styles/tyyli8.css" />" rel="stylesheet"></c:when>
<c:otherwise><link href="<c:url value="/resources/styles/tyyli1.css" />" rel="stylesheet"></c:otherwise>
</c:choose>

<link href="<c:url value="/resources/img/favicon.ico" />" rel="shortcut icon" type="image/x-icon" />
<script type="application/javascript" charset="UTF-8" src="<c:url value="/resources/js/script.js" />"></script>

<!-- jqueryUI date- & timepicker stuff -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.4.5/jquery-ui-timepicker-addon.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.4.5/i18n/jquery-ui-timepicker-addon-i18n.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.4.5/i18n/jquery-ui-timepicker-fi.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.4.5/jquery-ui-sliderAccess.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.4.5/jquery-ui-timepicker-addon.js"></script>
<script src="<c:url value="/resources/js/datepickerui-fi.js" />"></script>
<script src="<c:url value="/resources/js/timepickerui-fi.js" />"></script>
</head>

<body>

<!-- FORM[0]: DELETE -->
<form id="delForm" action="del" method="post">
<input type="hidden" id="delTask" name="delTask" value="0" />
</form>
<!-- FORM[1]: EDIT -->
<form id="editForm" action="edit" method="post">
<input type="hidden" id="editTask" name="editTask" value="0" />
</form>
<!-- FORM[2]: SHARE -->
<form id="shareForm" action="share" method="post">
<input type="hidden" id="shareTask" name="shareTask" value="0" />
<input type="hidden" id="groupID" name="groupID" value="0" />
</form>
<!-- FORM[3]: CANCEL -->
<form id="cancelForm" action="cancel" method="post">
</form>
<!-- FORM[4]: THEME -->
<form id="themeForm" action="theme" method="post">
<input type="hidden" id="themeID" name="themeID" value="0" />
</form>
<!-- FORM[5]: ACTIVATE -->
<form id="activationForm" action="activation" method="post">
<input type="hidden" id="activeTask" name="activeTask" value="0" />
</form>

<div class="container">

	<!-- 2nd main row: task creation and editing form -->
	<!-- If editing is not active (edit==0) font color is black, otherwise red -->
	<div class="row ${edit=='0' ? 'label-color' : 'label-color-edit'}">
	
	<!-- if editing is not active, show header '+ Luo uusi tehtava' -->
	<c:if test="${edit=='0'}">
	
	<!-- 1st optional inner row -->
		<div class="row">
			<div class="col-xs-offset-2 col-xs-7">
			<h3><span data-toggle="collapse" data-target="#add" style="cursor: pointer;">&nbsp;&nbsp;+ Luo uusi tehtävä</span></h3>
			</div>
			
			<!-- theme selectors -->
			<div class="col-xs-3">
				<div class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="false">
				<img src="<c:url value="/resources/img/brush.png" />" style="margin: 16px 0 0 20px; width: 25px;" />
				</a>
				<ul class="dropdown-menu dropdown-menu-left">
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=1;document.forms[4].submit();">Grind (sinivihreä)</a>
				</li>
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=2;document.forms[4].submit();">LeafFrog (vihreä)</a>
				</li>
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=3;document.forms[4].submit();">GrayStone (siniharmaa)</a>
				</li>
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=4;document.forms[4].submit();">Amethyst (purppura)</a>
				</li>
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=5;document.forms[4].submit();">Brownie (ruskea)</a>
				</li>
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=6;document.forms[4].submit();">Vampire (mustaharmaa)</a>
				</li>
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=7;document.forms[4].submit();">Melancholia (harmaa)</a>
				</li>
				<li class="theme-selected">
				<a href="#" onclick="document.forms[4].themeID.value=8;document.forms[4].submit();">Blanco (väritön)</a>
				</li>
				</ul>
				</div>
			</div>
		</div>
	</c:if>
	
	<!-- if editing is active (edit==1), show header 'Muokkaa tehtavaa' -->
	<c:if test="${edit=='1'}">
	
	<!-- 1st optional inner row -->
	<div class="row">
		<div class="col-xs-offset-2 col-xs-10">
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
		
		<div class="row ${edit=='0' ? 'label-color' : 'label-color-edit'}">
			<div class="form-group">
			<!-- bootstrap class control-label sets the label before input field (to the left) with alignment right -->
			<label class="control-label col-xs-offset-1 col-xs-2" for="kuvaus">* Tehtävä:</label>
				<div class="col-xs-6">
				<!-- spring form: cssClass = class (html) -->
				<form:input path="kuvaus" cssClass="form-control" placeholder="Kirjoita tehtävä (pakollinen)" required="required" maxlength="80" />
				</div>
				<div class="col-xs-3"></div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
			<label class="control-label col-xs-offset-1 col-xs-2" for="ryhma">Ryhmätunnus:</label>
				<div class="col-xs-6">
				<form:input path="ryhma" cssClass="form-control" style="text-transform: uppercase" maxlength="50" />
				</div>
				<div class="col-xs-3"></div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
			<label class="control-label col-xs-offset-1 col-xs-2" for="kuvaus">Kommentit:</label>
				<div class="col-xs-6">
				<!-- bootstrap class form-control makes the element full width of parent element in a form -->
				<form:textarea path="tiedot" cssClass="form-control" rows="5" placeholder="Kirjoita keskustelun aloitus (valinnainen)" maxlength="1000" /> 
				</div>
				<div class="col-xs-3"></div>
			</div>
		</div>
		
		<!-- bootstrap class has-feedback puts glyphicon icon inside the input field -->
		<div class="row">
			<div class="form-group has-feedback">
			<label class="control-label col-xs-offset-1 col-xs-2" for="kuvaus">Ajankohta:</label>
				<div class="controls bootstrap-timepicker col-xs-3" >
				<form:input path="ajankohtaPvm" cssClass="form-control text-center datetime" id="datepicker" onfocus="this.blur();" />
				<i class="form-control-feedback glyphicon glyphicon-calendar"></i>
				</div>
				<div class="col-xs-3">
				<form:input path="ajankohtaKlo" cssClass="form-control text-center" id="timepicker" onfocus="this.blur();" />
				<!-- bootstrap form-control-feedback puts glyphicon icon to the right end of the input field (not after text content) -->
				<i class="form-control-feedback glyphicon glyphicon-time"></i>
				</div>
				<div class="col-xs-3"></div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group"> 
				<div class="col-xs-offset-3 col-xs-3">
				<button type="submit" class="btn btn-default form-control"><span class="glyphicon glyphicon-download-alt"></span>&nbsp;&nbsp;TALLENNA</button>
				</div>
				<div class="col-xs-3">
				<button onclick="document.forms[3].submit(); return false;" class="btn btn-default form-control"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;PERUUTA</button>
				</div>
				<div class="col-xs-3"></div>
			</div>
		</div>
		
		</form:form>
		</div>
	</div>
	
	<!-- UI gets list of all tasks (tehtavat) from controller and prints them if not empty -->
	<c:if test="${empty tehtavat}">
	
	<!-- 3rd optional main row -->
	<div class="row">
		<div class="col-xs-offset-2 col-xs-10">
		Sinulla ei ole tehtäviä
		</div>
	</div>
	</c:if>
	
	<!-- 3rd optional main row -->
	<c:if test="${not empty tehtavat}">
	
	<!-- task content boxes generated in loop -->
	<c:forEach var="t" items="${tehtavat}" varStatus="loop">
	
	<fmt:parseDate value="${t.ajankohtaPvm} ${t.ajankohtaKlo}" pattern="yyyy-MM-dd HH:mm" var="parsedAjankohta" type="date" />
	<fmt:parseDate value="${t.ajankohtaPvm}" var="compTaskDate" pattern="yyyy-MM-dd" />
	<fmt:parseDate value="1970-01-01" var="compIdentifier" pattern="yyyy-MM-dd" />
	
	<div class="row">
		<!-- bootstrap class well is a simple styled content box. 2 cols empty, 8 cols for well, 2 cols empty (total 12) -->
		<div class="col-xs-offset-2 col-xs-8 well ${(parsedAjankohta > now) ? 'mark-task' : ((compTaskDate == compIdentifier) ? 'mark-note' : 'mark-warn')}">
	
		<!-- dropdown list of optional functions of a single task -->
		<div class="task-options">
			<div class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="true"><span class="glyphicon glyphicon-triangle-bottom drop-glyph" aria-hidden="true" style="margin: 8px 8px 0 0;"></span></a>
			<ul class="dropdown-menu dropdown-menu-right">
				<li>
				<!-- edit option: on click change value of editTask attribute in edit form and submit the form -->
				<a href="#" onclick="document.forms[1].editTask.value='${t.id}';document.forms[1].submit();"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Muokkaa</a>
				</li>
				<li>
				<a href="#" onclick="sh=prompt('Anna ryhmätunnus:','${t.ryhma}');if(sh!=null){document.forms[2].shareTask.value='${t.id}';document.forms[2].groupID.value=sh;document.forms[2].submit();}"><span class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;Jaa...</a>
				</li>
				<li role="separator" class="divider"></li>
				<li>
				
				<!-- remove option: on click change value of delTask attribute in delete form and submit the form -->
				<a href="#" onclick="if(!confirm('Haluatko poistaa tehtävän pysyvästi?')){return false;}else{document.forms[0].delTask.value='${t.id}';document.forms[0].submit();}"><span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;Poista</a>
				</li>
			</ul>
			</div>
		</div>
	
		<span>
		<small>
		<c:if test="${compTaskDate != compIdentifier}">
		<fmt:parseDate value="${t.ajankohtaPvm}" pattern="yyyy-MM-dd" var="parsedAjankohtaPvm" type="date" />
		<fmt:parseDate value="${t.ajankohtaKlo}" pattern="HH:mm" var="parsedAjankohtaKlo" type="time" />
		<fmt:formatDate value="${parsedAjankohtaPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
		<fmt:formatDate value="${parsedAjankohtaKlo}" pattern="HH:mm" type="time" />&nbsp;
		<span style="font-style: italic;">
		<c:out value="${(parsedAjankohta > now) ? '' : (compTaskDate == compIdentifier ? '' : 'Ajankohta ylitetty')}" escapeXml="false" />
		</span>
		<!-- <span style="font-style: italic;"> [ julkaissut: <c:out value="${t.user}" /> ] </span> -->
		<br>
		</c:if>
		</small>
		</span>
		<span><c:out value="${t.kuvaus}" /></span>
		
		<c:if test="${not empty t.ryhma}">
		<div class="groupid"><c:out value="${t.ryhma}" /></div>
		</c:if>
		
		<span onclick="document.forms[5].activeTask.value=${t.id};document.forms[5].submit();" style="cursor: pointer;">&nbsp;&#8811;&nbsp;&nbsp;</span>
		
		<c:if test="${activeTask == t.id}">
		<div>
		<pre style="min-height: 30px; margin-bottom: -15px;"><c:out value="${t.tiedot}" /></pre><br>		
		<form action="comment" id="commentForm" method="post" style="white-space: nowrap;">
		<input type="hidden" id="commentedText" name="commentedText" value="${t.tiedot}" />
		<input type="hidden" id="commentedTask" name="commentedTask" value="${t.id}" />
		<input class="focus-unstyled" autocomplete="off" autofocus required type="text" id="commentInput" name="commentInput" maxlength="80" style="padding: 0 5px 0 5px; font: normal 12px Verdana; color: #000000; margin: -15px 0 0 -2px; border: 0; border-radius: 1px; width: 85%; height: 25px;" />
		<input type="submit" value="Kommentoi" onclick="this.form.elements['commentedText'].value+='\n${t.user}: '+this.form.elements['commentInput'].value+' (<fmt:formatDate value="${now}" pattern="MM.dd.yyyy HH:mm" />)';" style="font: normal 12px Verdana; color: #000000; margin: -17px 0 0 0; width: 15%; border: 0; border-radius: 1px; height: 25px;" />		
		</form>		
		</div>
		</c:if>
		
		</div>
		<div class="col-xs-2"></div>
	</div>
	
	</c:forEach>
	</c:if>
</div>



<!-- Banner -->
<div id="banner">
	<c:choose>
	<c:when test="${theme == 1}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-blue-green.png" />" /></c:when>
	<c:when test="${theme == 2}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-frog-pink.png" />" /></c:when>
	<c:when test="${theme == 3}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-blue-orange.png" />" /></c:when>
	<c:when test="${theme == 4}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-purple-green.png" />" /></c:when>
	<c:when test="${theme == 5}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-brown-blue.png" />" /></c:when>
	<c:when test="${theme == 6}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-gray-black.png" />" /></c:when>
	<c:when test="${theme == 7}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-dark-gray.png" />" /></c:when>
	<c:when test="${theme == 8}"><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-no-color.png" />" /></c:when>
	<c:otherwise><img id="grind-logo" src="<c:url value="/resources/img/grind-logo-blue-green.png" />" /></c:otherwise>
	</c:choose>
</div>
</body>
</html>