<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="false"%>

<html>

<head>
<!-- bootstrap and other libraries (jquery and css) -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value="/resources/styles/style-common.css" />" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="resources/js/script.js">"></script>

<title>Tervetuloa</title>
</head>

<body onload="document.loginForm.u_user.focus();">
	
	<div class="container" style="text-align: center !important;">
		
		<div id="loginylaosa">
		</div>
		
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8">
				<h3>Kirjaudu sisään</h3>	
				<c:if test="${not empty logout}">
					<p style="color: green">${logout}</p>
				</c:if>
				<c:if test="${not empty error}">
					<p style="color: red">${error}</p>
				</c:if>
				<c:if test="${not empty success}">
					<p style="color: green">${success}</p>
				</c:if>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-offset-2 col-sm-8">
				<form role="form" class="form-horizontal" name="loginForm" action="login?logout=true" method="post">
					
					<div class="row">
					<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4"><input class="form-control" type="text" name="u_user" placeholder="Käyttäjänimi" autocomplete="off" style="text-align: center !important;" /></div>
					</div>
					</div>
					
					<div class="row">
					<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4"><input class="form-control" type="password" name="u_password" placeholder="Salasana" autocomplete="off" style="text-align: center !important;" /></div>
					</div>
					</div>
					
					<div class="row">
					<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4"><input style="background-color: #c0c0c0;" class="btn btn-default form-control login-submit" name="submit" type="submit" value="KIRJAUDU" /></div>
					</div>
					</div>
					
					<sec:csrfInput />
					
				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-offset-2 col-sm-8" >
				<h5><span data-toggle="collapse" data-target="#rekisteroidy" id="rekisteroitymisnappi">Rekisteröidy tästä</span></h5>
				<c:if test="${not empty userExistsError}">
					<p style="color: red">${userExistsError}</p>
				</c:if>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-offset-2 col-sm-8 collapse" id="rekisteroidy">		
				<form:form role="form" cssClass="form-horizontal" modelAttribute="user" action="registration" method="post" enctype="utf8">
					<div class="row">
					<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">	
					<form:input path="username" class="form-control" type="text" placeholder="Käyttäjänimi" autocomplete="off" style="text-transform: lowercase; text-align: center !important;" />
					</div>
					<div class="col-sm-4" style="text-align: left !important; font-size: 14px; line-height: 34px;">
					<span id="usrMsg"><form:errors path="username" style="color:red" />
					</span></div>
					</div>
					</div>
						
					<div class="row">
					<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
					<form:input path="password" class="form-control" type="password" placeholder="Salasana" autocomplete="off"  style="text-align: center !important;" /><form:errors path="password" style="color:red" />
					</div>
					<div class="col-sm-4" style="text-align: left !important; font-size: 14px; line-height: 34px;">
					<span id="pwMsg"></span><form:errors path="password" style="color:red" />
					</div>
					</div>
					</div>
						
					<div class="row">
					<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
					<input id="confirm_password" name="confirm_password" class="form-control" type="password" placeholder="Salasana uudestaan" autocomplete="off" style="text-align: center !important;" />
					</div>
					<div class="col-sm-4" style="text-align: left !important; font-size: 14px; line-height: 34px;">
					<span id="cpwMsg"></span><form:errors path="password" style="color:red" />
					</div>
					</div>
					</div>
						
					<div class="row">
					<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4"><input class="btn btn-default form-control login-submit" name="submit" type="submit" id="regSubmit" value="REKISTERÖIDY" disabled/>
					</div>
					</div>
					</div>
				</form:form>
			</div>
		</div>

		<script>
			$('#username').on('keyup', function() {
				if ($(this).val().length < 1 || $(this).val().length > 20) {
					$('#usrMsg').html('Käyttäjänimen pituus 1-20 merkkiä').css('color', 'red');
					document.getElementById('regSubmit').disabled = true;
				} else {
					$('#usrMsg').html('Käyttäjänimen pituus 1-20 merkkiä').css('color', 'green');
					if ($('#confirm_password').val() == $('#password').val() && $('#password').val()!="") {
						document.getElementById('regSubmit').disabled = false;
					}
				}
			});

			$('#password').on('keyup', function() {
				if ($(this).val().length < 8 || $(this).val().length > 60) {
					$('#pwMsg').html('Salasanan pituus 8-60 merkkiä').css('color', 'red');
					if($(this).val() != $('#confirm_password').val()){
						$('#cpwMsg').html('Salasanat eivät täsmää!').css('color', 'red');
					}
					document.getElementById('regSubmit').disabled = true;
				} else {
					$('#pwMsg').html('Salasanan pituus 8-60 merkkiä').css('color', 'green');
					if($(this).val() == $('#confirm_password').val()){
						$('#cpwMsg').html('Salasanat täsmäävät!').css('color', 'green');
						document.getElementById('regSubmit').disabled = false;
					}
				}
			});

			$('#confirm_password').on('keyup', function() {
				if ($(this).val() == $('#password').val() && $('#password').val().length >= 8 && $('#password').val().length <=60) {
					$('#cpwMsg').html('Salasanat täsmäävät!').css('color', 'green');
					document.getElementById('regSubmit').disabled = false;
				} 
				else if($('#password').val().length < 8 || $('#password').val().length > 60){
					$('#pwMsg').html('Salasanan pituus 8-60 merkkiä').css('color', 'red');
					document.getElementById('regSubmit').disabled = true;
				}
				else {
					$('#cpwMsg').html('Salasanat eivät täsmää!').css('color', 'red');
					document.getElementById('regSubmit').disabled = true;
				}
			});
		</script>
	</div>

<div id="banner" style="
background-image: linear-gradient(bottom, #337d73 50%, #2c6b59 50%);
background-image: -o-linear-gradient(bottom, #337d73 50%, #2c6b59 50%);
background-image: -moz-linear-gradient(bottom, #337d73 50%, #2c6b59 50%);
background-image: -webkit-linear-gradient(bottom, #337d73 50%, #2c6b59 50%);
background-image: -ms-linear-gradient(bottom, #337d73 50%, #2c6b59 50%);
background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0.5, #337d73), color-stop(0.5, #2c6b59));">
<img id="grind-logo" src="<c:url value="/resources/img/grind-logo-blue-green.png" />" />
</div>
</body>
</html>