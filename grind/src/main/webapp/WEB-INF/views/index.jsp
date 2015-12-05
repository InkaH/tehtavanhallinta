<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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

<!-- FORM[0]: DELETE TASK -->
<form id="delForm" action="del" method="post">
<input type="hidden" id="delTask" name="delTask" value="0" />
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[1]: EDIT -->
<form id="editForm" action="edit" method="post">
<input type="hidden" id="editTask" name="editTask" value="0" />
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[2]: SHARE -->
<form id="shareForm" action="share" method="post">
<input type="hidden" id="shareTask" name="shareTask" value="0" />
<input type="hidden" id="groupID" name="groupID" value="0" />
<input type="hidden" id="shareStatus" name="shareStatus" value="0" />
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[3]: CANCEL -->
<form id="cancelForm" action="cancel" method="post">
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[4]: THEME -->
<form id="themeForm" action="theme" method="post">
<input type="hidden" id="themeID" name="themeID" value="0" />
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[5]: ACTIVATE -->
<form id="activationForm" action="activation" method="post">
<input type="hidden" id="activeTask" name="activeTask" value="0" />
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[6]: DELETE COMMENT -->
<form id="delCommentForm" action="delComment" method="post">
<input type="hidden" id="delComment" name="delComment" value="0" />
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[7]: LOGOUT -->
<c:url var="logoutUrl" value="/logout"/>
<form id="logoutForm" action="logout" method="post">
<input type="hidden" name="scrollPos" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[8]: TAB CHENGE -->
<form id="tabForm" action="tabChange" method="post">
<input type="hidden" id="tabID" name="tabID" value="0" />
<sec:csrfInput />
</form>
<!-- FORM[9]: SET DONE -->
<form id="doneForm" action="setDone" method="post">
<input type="hidden" id="doneID" name="doneID" value="0" />
<input type="hidden" id="doneValue" name="doneValue" value="0" />
<sec:csrfInput />
</form>

<div class="container">
	
	<div class="col-sm-offset-2 col-sm-8" style="margin-top: 15px;">
	
	<ul class="nav nav-tabs">
    <li class="${activeTab == 0 ? 'active' : ''}"><a data-toggle="tab" href="#" onclick="this.blur();document.forms[8].tabID.value=0;document.forms[8].submit();">Omat</a></li>
    <li class="${activeTab == 2 ? 'active' : ''}"><a data-toggle="tab" href="#" onclick="this.blur();document.forms[8].tabID.value=2;document.forms[8].submit();">Tehdyt</a></li>
    <li class="${activeTab == 1 ? 'active' : ''}"><a data-toggle="tab" href="#" onclick="this.blur();document.forms[8].tabID.value=1;document.forms[8].submit();">Ryhmät</a></li>
  	</ul>
		
	<c:if test="${edit=='0'}">
	<div class="row ${edit=='0' ? 'label-color' : 'label-color-edit'}" style="white-space: nowrap;">
	<div class="col-sm-12" style="display: block; width: 100%;">
		
	
	<div class="dropdown" style="display: block;">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="false">
	<img src="<c:url value="/resources/img/cog.png" />" style="float: right; margin: 9px 5px 0 0; width: 22px;" />
	</a>
	<ul class="dropdown-menu dropdown-menu-right" style="margin: 37px -14px 0 0;">
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
	<h4 style="line-height: 42px; display: inline;"><span data-toggle="collapse" data-target="#add" style="cursor: pointer;">&nbsp;&nbsp;+ Luo uusi tehtävä</span></h4>	
	</div>
	</div>
	</c:if>
		
	<c:if test="${edit=='1'}">
	<div class="row">
	<div class="col-sm-12 ${edit=='0' ? 'label-color' : 'label-color-edit'}">
	<h4>Muokkaa tehtävää</h4>
	</div>
	</div>
	</c:if>
	
	<div class="row">
	<div class="col-sm-12">
	<div id="add" class="${edit=='0' ? 'collapse' : 'collapse in'}">
		<form:form role="form" class="form-horizontal" modelAttribute="newTask" action="add" method="post" accept-charset="UTF-8">
		<form:hidden path="id" />
		<form:hidden path="done" />
		
		<div class="row  ${edit=='0' ? 'label-color' : 'label-color-edit'}">
			<div class="form-group">
			<label class="control-label col-sm-2" for="kuvaus">* Tehtävä:</label>
				<div class="col-sm-8">
				<!-- bootstrap class form-control makes the element full width of parent element in a form -->
				<form:textarea path="task" required="required" cssClass="form-control" rows="5" placeholder="Kirjoita tehtävä (pakollinen)" maxlength="1000" /> 
				</div>
			</div>
		</div>
		
		<div class="row  ${edit=='0' ? 'label-color' : 'label-color-edit'}">
			<div class="form-group">
			<label class="control-label col-sm-2" for="ryhma">Ryhmätunnus:</label>
				<div class="col-sm-8">
				<form:input path="group" cssClass="form-control" placeholder="Kirjoita ryhmätunnus (valinnainen)" style="text-transform: uppercase" maxlength="50" />
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<form:checkbox path="shared" value="" />&nbsp;&nbsp;Jaetaan julkisena
				</div>
			</div>
		</div>
		
		
		
		<!-- bootstrap class has-feedback puts glyphicon icon inside the input field -->
		<div class="row  ${edit=='0' ? 'label-color' : 'label-color-edit'}">
			<div class="form-group has-feedback">
			<label class="control-label col-sm-2" for="kuvaus">Ajankohta:</label>
				<div class="controls bootstrap-timepicker col-sm-4" >
				<form:input path="date" cssClass="form-control text-center datetime" id="datepicker" onfocus="this.blur();" />
				<i class="form-control-feedback glyphicon glyphicon-calendar"></i>
				</div>
				<div class="col-sm-4">
				<form:input path="time" cssClass="form-control text-center" id="timepicker" onfocus="this.blur();" />
				<!-- bootstrap form-control-feedback puts glyphicon icon to the right end of the input field (not after text content) -->
				<i class="form-control-feedback glyphicon glyphicon-time"></i>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group"> 
				<div class="col-sm-offset-2 col-sm-4">
				<button type="submit" class="btn btn-default form-control"><span class="glyphicon glyphicon-download-alt"></span>&nbsp;&nbsp;TALLENNA</button>
				</div>
				<div class="col-sm-4">
				<button onclick="document.forms[3].submit(); return false;" class="btn btn-default form-control"><span class=""></span>&nbsp;&nbsp;PERUUTA</button>
				</div>
			</div>
		</div>
		
		</form:form>
	</div>
	</div>
	</div>	

	<c:if test="${activeTab == 1}">
	<div class="row" style="margin: 5px 0 10px 0;">
  	<label class="col-sm-2" for="groupSelection" style="font-weight: normal;">Ryhmätunnus:</label>
  	<div class="col-sm-8">
  	<form action="getGroupTasks" method="post">
  	<select id="groupSelection" name="groupSelection" onchange="this.form.submit();" style="width: 100%; padding: 0 5px 0 5px;">
    <c:choose>
    <c:when test="${groupListDefault == 'Kaikki'}"><option value="Kaikki" selected>Kaikki</option></c:when>
    <c:otherwise><option value="Kaikki">Kaikki</option></c:otherwise>
    </c:choose>    
  	<c:forEach var="g" items="${grouplist}" varStatus="loop-g">  
  	<c:choose>
    <c:when test="${groupListDefault == g}"><option value="${g}" selected><c:out value="${g}" /></option></c:when>
    <c:otherwise><option value="${g}"><c:out value="${g}" /></option></c:otherwise>
    </c:choose>    
    </c:forEach>
  	</select>
	<sec:csrfInput />
  	</form>
  	</div>
	</div>
	</c:if>	
	
	<c:if test="${empty tasks}">
	<div class="row">
	<div class="col-sm-12">
	<br>Sinulla ei ole tehtäviä ...
	</div>
	</div>
	</c:if>
	
	<c:if test="${not empty tasks}">
	<c:forEach var="t" items="${tasks}" varStatus="loop">
	<fmt:parseDate value="${t.date} ${t.time}" pattern="yyyy-MM-dd HH:mm" var="parsedAjankohta" type="date" />
	<fmt:parseDate value="${t.date}" var="compTaskDate" pattern="yyyy-MM-dd" />
	<fmt:parseDate value="1970-01-01" var="compIdentifier" pattern="yyyy-MM-dd" />
	
	<div class="row">
	<div class="col-sm-12 well ${(t.done == 0 && parsedAjankohta > now) ? 'mark-task' : (t.done != 0 ? 'mark-task' : ((compTaskDate == compIdentifier) ? 'mark-note' : 'mark-warn'))}">
	<c:if test="${user == t.user}">
	<div class="task-options">
	<div class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="false" aria-expanded="true"><span class="glyphicon glyphicon-triangle-bottom drop-glyph" aria-hidden="true" style="margin: 8px 8px 0 0;"></span></a>
	<ul class="dropdown-menu dropdown-menu-right">
	
	<c:if test="${t.done == 1}" >
	<li>
	<a href="#" onclick="if(!confirm('Palautetaanko tehtävä tekemättömiin?')){return false;}else{document.forms[9].doneID.value='${t.id}';document.forms[9].doneValue.value='0';document.forms[9].submit();}"><span class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;Palauta</a>
	</li>
	</c:if>
	
	<c:if test="${t.done == 0}">
	
	<li>
	<a href="#" onclick="if(!confirm('Siirretäänkö tehtävä tehtyihin tehtäviin?')){return false;}else{document.forms[9].doneID.value='${t.id}';document.forms[9].doneValue.value='1';document.forms[9].submit();}"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;Merkitse tehdyksi</a>
	</li>	
	
	<li>
	<a href="#" onclick="document.forms[1].editTask.value='${t.id}';document.forms[1].submit();"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Muokkaa</a>
	</li>
	
		<c:if test="${!t.shared}">
		<li>
		<a href="#" onclick="sh=prompt('Jaa tehtävä julkisesti ryhmätunnuksella:','${t.group}');if(sh!=null){document.forms[2].shareStatus.value='true';document.forms[2].shareTask.value='${t.id}';document.forms[2].groupID.value=sh;document.forms[2].submit();}else{return false;}"><span class="glyphicon glyphicon-share-alt"></span>&nbsp;&nbsp;Jaa...</a>
		</li>		
		<li role="separator" class="divider"></li>
		</c:if>
	
	</c:if>
	
		<c:if test="${!t.shared}">
		<li>
		<a href="#" onclick="if(!confirm('Haluatko poistaa tehtävän pysyvästi?')){return false;}else{document.forms[0].delTask.value='${t.id}';document.forms[0].submit();}"><span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;Poista</a>
		</li>
		</c:if>
	
	</ul>
	</div>
	</div>
	</c:if>
	
	<span>
	<small style="font-weight: bold;">
	<c:if test="${compTaskDate != compIdentifier}">
	<fmt:parseDate value="${t.date}" pattern="yyyy-MM-dd" var="parsedAjankohtaPvm" type="date" />
	<fmt:parseDate value="${t.time}" pattern="HH:mm" var="parsedAjankohtaKlo" type="time" />
	<fmt:formatDate value="${parsedAjankohtaPvm}" pattern="d.M.yyyy" type="date" />&nbsp;
	<fmt:formatDate value="${parsedAjankohtaKlo}" pattern="HH:mm" type="time" />&nbsp;
	<span>
	<c:out value="${(parsedAjankohta > now) ? '' : (t.done != 0 ? '' : (compTaskDate == compIdentifier ? '' : 'Ajankohta ylitetty'))}" />
	<c:choose>
	<c:when test="${user == t.user && activeTab != 0 && activeTab != 2}"><div class="username-tag" style="text-transform: uppercase;"><c:out value="${t.user}" /></div></c:when>
	<c:when test="${activeTab == 1}"><div class="username-tag" style="text-transform: uppercase;"><c:out value="${t.user}" /></div></c:when>
	</c:choose>
	</span>
	</c:if>
	</small>
	</span>
	<div class="task-elem" onclick="document.forms[5].activeTask.value=${t.id};document.forms[5].submit();" style="cursor: pointer;"><c:out value="${t.task}" /></div>
	<c:if test="${not empty t.group}">
	<div class="groupid"><small><c:out value="${t.shared ? 'Jaettu&nbsp;&nbsp;&#8811;&nbsp;&nbsp;' : ''}" escapeXml="false" /><c:out value="${t.group}" /></small></div>
	</c:if>
	
	
	<c:if test="${activeTask == t.id}">
	<div>
	<div id="comment-area">
	<c:if test="${not empty comments}">
	<table id="comment-table">
	<c:forEach var="c" items="${comments}" varStatus="c-loop">
	<fmt:parseDate value="${c.date}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
	<fmt:parseDate value="${c.time}" pattern="HH:mm" var="parsedTime" type="time" />
	<tr>
	<td>
	<span>
	<span style="font-weight: ${user == c.user ? 'bold' : 'normal'};"><c:out value="${c.user}" /></span>:&nbsp;<c:out value="${c.comment}" />
	</span>
	</td>
	<td>
	<c:if test="${c.user == user}">
	<span onclick="if(!confirm('Haluatko poistaa kommentin pysyvästi?')){return false;}else{document.forms[6].delComment.value='${c.id}';document.forms[6].submit();}" class="glyphicon glyphicon-remove" style="cursor: pointer;"></span>
	</c:if>&nbsp;
	<span class="comment-remove">
	<fmt:formatDate value="${parsedDate}" pattern="d.M.yyyy" type="date" />&nbsp;<fmt:formatDate value="${parsedTime}" pattern="HH:mm" type="time" />
	</span>
	</td>
	</tr>
	</c:forEach>
	</table>
	</c:if>
	</div>	
	<div style="white-space: nowrap;">
	<form:form role="form" modelAttribute="newComment" action="addComment" method="post" accept-charset="UTF-8">
	<form:hidden path="id" value="0" />
	<form:input path="comment" required="required" autocomplete="off" onclick="this.focus();" maxlength="500" style="padding: 0 137px 0 5px; font: normal 12px Verdana; color: #000000; margin-bottom: 3px; margin-right: -130px; border: 0; border-radius: 0; width: 100%; height: 25px;" />
	<fmt:formatDate var="dateNow" value="${now}" pattern="d.M.yyyy" type="date" />
	<form:hidden path="date" value="${dateNow}" />
	<fmt:formatDate var="timeNow" value="${now}" pattern="HH:mm" type="time" />
	<form:hidden path="time" value="${timeNow}" />
	<form:hidden path="task" value="${t.id}" />
	<form:hidden path="user" value="${user}" />
	<input type="submit" id="commentSubmit" value="Kommentoi" style="font: normal 12px Verdana; color: #000000; margin: 0 0 0 -5px; width: 130px; border: 0; box-shadow: none; height: 25px;" />		
	</form:form>	
	</div>	
	</div>
	</c:if>
	</div>
	</div>
	</c:forEach>
	</c:if>
	
		
	<!-- End of first big bootstrap grid item -->
	</div>
	
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
	
	<div id="logged">
	<span style="text-transform: uppercase;">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
    <c:out value="${pageContext.request.userPrincipal.name}" />
    </c:if>
	</span>&nbsp;-&nbsp;&nbsp;<a href="#" onclick="document.forms[7].submit();">Kirjaudu ulos</a>	
	</div>
	
	<div id="logged-mobile"><a href="#" onclick="document.forms[7].submit();">EXIT</a>	
	</div>
	
</div>
</body>
</html>