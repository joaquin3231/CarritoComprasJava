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
			<h2 class="tituloPag">Registro</h2>
				<form:form action="/register" method="POST" modelAttribute="newUser" class="form">
					<div  class="divForm">
						<form:label path="firsName" class="label">Nombre:</form:label>
						<form:input path="firsName" class="input"  required="required"/>
						<form:errors path="firsName" class="error" />
					</div>
					
					<div class="divForm">
						<form:label path="lastName" class="label">Apellido:</form:label>
						<form:input path="lastName" class="input" required="required"/>
						<form:errors path="lastName" class="error" />
					</div>
					
					<div class="divForm">
						<form:label path="type" class="label"> Tipo de Usuario :</form:label>
						<form:select path="type" class="select">
							<form:option value="Comprador">Comprador</form:option>
							<form:option value="Vendedor">Vendedor</form:option>
						</form:select>
					</div>
					
					<div class="divForm">
						<form:label path="email" class="label">E-Mail:</form:label>
						<form:input path="email" class="input" required="required"/>
						<form:errors path="email" class="error" />
					</div>
					
					<div class="divForm">
						<form:label path="password" class="label">Contraseña:</form:label>
						<form:password path="password" class="input" required="required"/>
						<form:errors path="password" class="error" />
					</div>
					
					<div class="divForm">
						<form:label path="comfirm" class="label">Confirmar Contraseña:</form:label>
						<form:password path="comfirm" class="input" required="required"/>
						<form:errors path="comfirm" class="error" />
					</div>
					<p> Ya tienes cuenta? <a id="inicioSesion" href="/inicioSesion">Inicia sesion</a></p>
					<input type="submit" class="submit" value="registrarme">
				</form:form>
		</div>
	</div>

</body>
</html>