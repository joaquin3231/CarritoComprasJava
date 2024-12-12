<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	
	<div class="contenedor">
		<div>
			<h2 class="tituloPag">Inicio sesion</h2>
			<form action="/login" method="POST" class="form">
				<p class="error"> ${ errorLogin } </p>
				<div class="divForm">
					<label class="label">E-mail:</label>
					<input type="email" class="input" name="email">
				</div>
				
				<div class="divForm">
					<label class="label">Password:</label>
					<input type="password" class="input" name="password">
				</div>
				<p> Eres nuevo? <a href="/">registrate</a></p>
				
				<input type="submit" class="submit" value="Inicio Sesion">
			</form>
		</div>
	</div>
	
</body>
</html>