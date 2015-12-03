<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@page session="false"%>
<html>
   <head>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
      <title>Tervetuloa</title>
   </head>

   <body onload='document.loginForm.username.focus();'>
      <div>
         <h3>Kirjaudu sisään</h3>
         <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
         </c:if>
         <c:if test="${not empty msg}">
            <p style="color:red">${msg}</p>
         </c:if>
         <c:if test="${not empty success}">
            <p style="color:green">${success}</p>
         </c:if>
         <form name='loginForm' action="login" method='POST'>
            <table>
               <tr>
                  <td>Käyttäjänimi:</td>
                  <td><input type='text' name='u_user'></td>
               </tr>
               <tr>
                  <td>Salasana:</td>
                  <td><input type='password' name='u_password' /></td>
               </tr>
               <tr>
                  <td colspan='2'><input name="submit" type="submit"
                     value="Kirjaudu" /></td>
               </tr>
            </table>
            <sec:csrfInput />
         </form>
      </div>
      <div>
         <h3>Rekisteröidy</h3>
         <c:if test="${not empty userExistsError}">
            <p style="color:red">${userExistsError}</p>
         </c:if>
         <form:form modelAttribute="user" action="registration" method="POST" enctype="utf8">
            <br>
            <table>
               <tr>
                  <td><label>Käyttäjänimi:</label>
                  </td>
                  <td>
                     <form:input path="username" value="" />
                     <span id='usrMsg'></span>
                  </td>
                  <form:errors path="username" style="color:red"/>
               </tr>
               <tr>
                  <td><label>Salasana:</label>
                  </td>
                  <td>
                     <form:input path="password" id="password" value="" type="password"/>
                     <span id='pwMsg'></span>
                  </td>
                  <form:errors path="password" style="color:red"/>
               </tr>
               <tr>
                  <td><label>Toista salasana:</label>
                  </td>
                  <td><input id="confirm_password" value="" type="password"/><span id='cpwMsg'></span></td>
               </tr>
            </table>
            <button type="submit" id="submit" disabled>Rekisteröidy</button>
         </form:form>
      </div>
      <script>
         $('#username').on('keyup', function() {
         	if ($(this).val().length < 1 || $(this).val().length > 20){
         		$('#usrMsg').html('Käyttäjänimen pituus 1-20 merkkiä').css('color', 'red');
         		document.getElementById('submit').disabled = true;
         	} else {
         		$('#usrMsg').html('Käyttäjänimen pituus 1-20 merkkiä').css('color', 'green');
         		if ($('#confirm_password').val() == $('#password').val()){
         			document.getElementById('submit').disabled = false;
         			}
         		}
         });
         
         $('#password').on('keyup', function() {
         	if ($(this).val().length < 8 || $(this).val().length > 60){
         		$('#pwMsg').html('Salasanan pituus 8-60 merkkiä').css('color', 'red');
         		document.getElementById('submit').disabled = true;
         	} else {
         		$('#pwMsg').html('Salasanan pituus 8-60 merkkiä').css('color', 'green');
         		if ($(confirm_password).val() == $('#password').val()){
         			document.getElementById('submit').disabled = false;
         			}
         	}
         });
         
         $('#confirm_password').on('keyup', function () {
         	if ($(this).val() == $('#password').val()) {
             	$('#cpwMsg').html('Salasanat täsmäävät!').css('color', 'green');
             	document.getElementById('submit').disabled = false;
         	} else {
         		$('#cpwMsg').html('Salasanat eivät täsmää!').css('color', 'red');
         		document.getElementById('submit').disabled = true;
         	}
         });    
      </script> 
   </body>
</html>