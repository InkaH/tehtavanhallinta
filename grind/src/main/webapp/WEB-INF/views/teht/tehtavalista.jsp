<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G R I N D</title>
<link rel="stylesheet" type="text/css" href="/styles/tyyli.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/js/javascript.js"></script> 

</head>
<body>
	<div class="container">
    	<div class="header">
			<h1>G R I N D</h1>
		</div>
		<div class="tasks">
			<div class="task">
				<c:if test="${empty tehtavat}">
					<p>Ei tehtäviä tietokannassa.</p>
				</c:if>
				<c:if test="${not empty tehtavat}">
					<c:forEach var="tehtava" items="${tehtavat}">
						<p>
						<c:out value="${tehtava.id}, ${tehtava.kuvaus}, ${tehtava.lisatiedot}, ${tehtava.status}, ${tehtava.pvm}, ${tehtava.aika}"/></p>
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
			<form class="form-inline" modelAttribute="tehtava" method="post">
			    <div class="form-group">
					<input id="task" type="text" class="form-control inline" placeholder="Kirjoita tähän seuraava tehtävä!" /> 
		        </div>
		        <div class="form-group">
		            <input id="info" type="text" class="form-control inline" placeholder="Kirjoita tähän lisätiedot" /> 
		            <!-- tähän se datepickeri -->
		        </div>
		        <div class="form-group">
		            <button id="add" type="submit" class="btn btn-default">Lisää</button>
		        </div>
			</form>
			
		</div>
	</div>
	

	
</body>
</html>