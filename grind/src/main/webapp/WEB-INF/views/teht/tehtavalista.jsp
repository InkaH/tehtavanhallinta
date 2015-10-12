<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>Henkilöiden haku kannasta</title>
<link rel="stylesheet" type="text/css" href="../resources/styles/common.css">
<link rel="stylesheet" type="text/css" href="../resources/styles/form.css">
</head>	
<body>		
	<h1>
		Tehtävät
	</h1>
	<c:if test="${empty tehtavat}">
		<p>Ei tehtäviä tietokannassa.</p>
	</c:if>
	<c:if test="${not empty tehtavat}">
		<c:forEach var="tehtava" items="${tehtavat}" varStatus="counter">
		<p><c:out value="${counter.count}. ${tehtava.id}, ${tehtava.kuvaus}, ${tehtava.lisatiedot}, ${tehtava.status}, ${tehtava.pvm}, ${tehtava.aika}"/></p>
		</c:forEach>
	</c:if>	
</body>
</html>